package jiaowuwang;


/*import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;*/
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;

//import org.apache.commons.httpclient.util.HttpURLConnection;

//import com.app.comom.FileUtil;

public class JiaoWuWang {
	public static void main(String[] args) throws Exception {
		String surl = "http://login.goodjobs.cn/index.php/action/UserLogin";  
		  

		URL url = new URL(surl);  
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();  

		connection.setDoOutput(true);  

		OutputStreamWriter out = new OutputStreamWriter(connection  
		        .getOutputStream(), "GBK");  
		            
		out.write("memberName=334159&password=zxc14142"); // post�Ĺؼ����ڣ�  
		// remember to clean up  
		out.flush();  
		out.close();  

		String cookieVal = connection.getHeaderField("Set-Cookie"); 
		System.out.println(cookieVal);
		 String s = "http://user.goodjobs.cn/dispatcher.php/module/Resume/action/Preview";  
		//���´�һ������  
		              url = new URL(s);  
		HttpURLConnection resumeConnection = (HttpURLConnection) url  
		        .openConnection();  
		if (cookieVal != null) {  
		                      //����cookie��Ϣ��ȥ���Ա����Լ�����ݣ�����ᱻ��Ϊû��Ȩ��  
		    resumeConnection.setRequestProperty("Cookie", cookieVal);  
		}  
		resumeConnection.connect();  
		InputStream urlStream = resumeConnection.getInputStream();  
		BufferedReader bufferedReader = new BufferedReader(  
		        new InputStreamReader(urlStream));  
		String ss = null;  
		String total = "";  
		while ((ss = bufferedReader.readLine()) != null) {  
		    total += ss;  
		}  
		IOUtils.write(total, new FileOutputStream("d:/index.html"));  
		bufferedReader.close();  
	}
	
}