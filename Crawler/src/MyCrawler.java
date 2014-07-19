import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.InputStream;

import javax.net.ssl.HttpsURLConnection;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;



public class MyCrawler {
	static String word; //����ȫ�־�̬�������ڴ洢�ؼ���
	public static void main(String args[]) throws Exception{
	  if(!createDir("D:\\crawlerDOWNLOAD")){  //��������ͼƬĿ¼
 			 System.exit(0);
 	   }
	   processContent(processURL(getURL()));
	}

	public static String getURL() throws IOException{         //��������Ĺؼ��ֵõ��ѹ�ͼƬ���������url
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));  //ʹ��BufferedReader ����ؼ���
		System.out.println("��������Ҫ�����Ĺؼ���");
		word = br.readLine();
		if(!createDir("D:\\crawlerDOWNLOAD\\�������-"+word)){
 			 System.exit(0);
 		}
		String searchWord = URLEncoder.encode(word,"UTF-8");  //���������ؼ��ֵĴ���ת��
		String url = "http://pic.sogou.com/d?query=";
		url+= searchWord;
		url+="&mood=0&picformat=0&mode=1&di=0&w=05009900&dr=1&did=4#did7";//����Ĺؼ�����Ϊ��ѡ���ͼģʽ�����ص���ͼ
		return url;
	}
	
	public static String processURL(String url){  //���ݵõ��ĵ�ַ�õ�html����
		HttpClient hc= new HttpClient();  //����HttpClientʵ��
		GetMethod getMethod = new GetMethod(url);//�������ӷ���ʵ��
		String content="";
		try {
			int statusCode = hc.executeMethod(getMethod);  //ִ��GetMethod ����
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			content = getMethod.getResponseBodyAsString();  //�õ�html������
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	
	public static void processContent(String content) throws Exception{
	     String str = "pic_url\":\"(http://.+?jpg)";  //ʹ��������ʽ����ͼƬ��ַ
	     Pattern p= Pattern.compile(str);        
         Matcher matcher = p.matcher(content);     
         String url;
         int i =0;
         BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
         System.out.println("��������Ҫ���ص�ͼƬ����");
         String num = br.readLine();//���û�������Ҫ���ص�ͼƬ����
         int n= Integer.parseInt(num);
         while(matcher.find()){
        	 if(i>=n){
        		 break;
        	 }
        	 i++;
        	 url=matcher.group(1);
        	 System.out.println("��"+i+"��ͼƬ������...");
        	 try{
        		 downloadPic(url,i);
        	 }
        	 catch(Exception e){
        		 i--;
        	 }
        	 Thread.sleep(1000);
         }
         System.out.println("������ɣ�  �ܹ�����ͼƬ�� "+i);
	}
	
	public static void downloadPic(String url,int name) throws Exception{
		
		String fileDes = "D:\\crawlerDOWNLOAD\\�������-"+word+"\\"+ name +".jpg";
		File toFile = new File(fileDes);
		while(toFile.exists()){
			name++;
			fileDes = "D:\\crawlerDOWNLOAD\\�������-"+word+"\\"+ name +".jpg";
			toFile = new File(fileDes);
		}
		
		toFile.createNewFile();
		FileOutputStream out =new FileOutputStream(toFile);
		try{
		out.write(getUrlFileData(url));
		out.close();
		}
		catch(Exception e){
			out.close();
			System.out.println("ͼƬ����ʧ��");
            toFile.delete();//�������ͼƬʧ�ܣ����ʧ�ܵ��ļ�ɾ��
			throw e;
		}
		System.out.println("ͼƬ���سɹ�");
		
	}
	
	public static byte[] getUrlFileData(String imgUrl) throws Exception{
		URL url = new URL(imgUrl);  
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.connect();
		InputStream in = conn.getInputStream();
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		byte [] buffer = new byte [1024];  //byte�������ڴ洢һ�ζ��������
		int len =0;
		while((len = in.read(buffer))!=-1){  //���read��������-1�����ʾ���ļ���ȫ�����롣
			b.write(buffer,0,len);
		}
		b.flush();
		in.close();
		byte [] fileData = b.toByteArray();
		b.close();
		return fileData;
		
	}
	
	
	public static boolean  createDir(String dirPath){  //����·�������ļ��еĺ���
		File dir=new File(dirPath);
		if(dir.exists()){
			System.out.println("Ŀ��Ŀ¼�Ѵ���");
			return true;
		}
		
		if(dir.mkdirs()){
			System.out.println("����Ŀ¼�ɹ�");
			return true;
		}
		else{
			System.out.println("����Ŀ¼ʧ��");
			return false;
		}
	}
	
}
