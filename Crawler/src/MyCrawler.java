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
	static String word; //建立全局静态变量用于存储关键字
	public static void main(String args[]) throws Exception{
	  if(!createDir("D:\\crawlerDOWNLOAD")){  //创建下载图片目录
 			 System.exit(0);
 	   }
	   processContent(processURL(getURL()));
	}

	public static String getURL() throws IOException{         //根据输入的关键字得到搜狗图片搜索引擎的url
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));  //使用BufferedReader 读入关键字
		System.out.println("请输入想要搜索的关键字");
		word = br.readLine();
		if(!createDir("D:\\crawlerDOWNLOAD\\搜索结果-"+word)){
 			 System.exit(0);
 		}
		String searchWord = URLEncoder.encode(word,"UTF-8");  //进行搜索关键字的代码转换
		String url = "http://pic.sogou.com/d?query=";
		url+= searchWord;
		url+="&mood=0&picformat=0&mode=1&di=0&w=05009900&dr=1&did=4#did7";//这里的关键字是为了选择大图模式以下载到大图
		return url;
	}
	
	public static String processURL(String url){  //根据得到的地址得到html内容
		HttpClient hc= new HttpClient();  //创建HttpClient实例
		GetMethod getMethod = new GetMethod(url);//建立连接方法实例
		String content="";
		try {
			int statusCode = hc.executeMethod(getMethod);  //执行GetMethod 方法
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			content = getMethod.getResponseBodyAsString();  //得到html的内容
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	
	public static void processContent(String content) throws Exception{
	     String str = "pic_url\":\"(http://.+?jpg)";  //使用正则表达式查找图片地址
	     Pattern p= Pattern.compile(str);        
         Matcher matcher = p.matcher(content);     
         String url;
         int i =0;
         BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
         System.out.println("请输入想要下载的图片数量");
         String num = br.readLine();//让用户输入需要下载的图片数量
         int n= Integer.parseInt(num);
         while(matcher.find()){
        	 if(i>=n){
        		 break;
        	 }
        	 i++;
        	 url=matcher.group(1);
        	 System.out.println("第"+i+"幅图片下载中...");
        	 try{
        		 downloadPic(url,i);
        	 }
        	 catch(Exception e){
        		 i--;
        	 }
        	 Thread.sleep(1000);
         }
         System.out.println("下载完成！  总共下载图片： "+i);
	}
	
	public static void downloadPic(String url,int name) throws Exception{
		
		String fileDes = "D:\\crawlerDOWNLOAD\\搜索结果-"+word+"\\"+ name +".jpg";
		File toFile = new File(fileDes);
		while(toFile.exists()){
			name++;
			fileDes = "D:\\crawlerDOWNLOAD\\搜索结果-"+word+"\\"+ name +".jpg";
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
			System.out.println("图片下载失败");
            toFile.delete();//如果下载图片失败，则把失败的文件删除
			throw e;
		}
		System.out.println("图片下载成功");
		
	}
	
	public static byte[] getUrlFileData(String imgUrl) throws Exception{
		URL url = new URL(imgUrl);  
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.connect();
		InputStream in = conn.getInputStream();
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		byte [] buffer = new byte [1024];  //byte数组用于存储一次读入的数据
		int len =0;
		while((len = in.read(buffer))!=-1){  //如果read方法返回-1，则表示该文件已全部读入。
			b.write(buffer,0,len);
		}
		b.flush();
		in.close();
		byte [] fileData = b.toByteArray();
		b.close();
		return fileData;
		
	}
	
	
	public static boolean  createDir(String dirPath){  //根据路径创建文件夹的函数
		File dir=new File(dirPath);
		if(dir.exists()){
			System.out.println("目标目录已存在");
			return true;
		}
		
		if(dir.mkdirs()){
			System.out.println("创建目录成功");
			return true;
		}
		else{
			System.out.println("创建目录失败");
			return false;
		}
	}
	
}
