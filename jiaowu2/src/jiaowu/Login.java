package jiaowu;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class Login {
	public static void main(String[] args) throws Exception{
		String surl = "http://jwbinfosys.zju.edu.cn/default2.aspx";

		/**
		 * ����Ҫ��URL�µ�URLConnection�Ի��� URLConnection���Ժ����׵Ĵ�URL�õ������磺 // Using
		 * java.net.URL and //java.net.URLConnection
		 */
		URL url = new URL(surl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		/**
		 * Ȼ���������Ϊ���ģʽ��URLConnectionͨ����Ϊ������ʹ�ã���������һ��Webҳ��
		 * ͨ����URLConnection��Ϊ���������԰����������Webҳ���͡��������������
		 */
		connection.setDoOutput(true);
		/**
		 * ���Ϊ�˵õ�OutputStream�������������Լ����Writer���ҷ���POST��Ϣ�У����磺 ...
		 */
		OutputStreamWriter out = new OutputStreamWriter(
				connection.getOutputStream(), "GBK");
		// ���е�memberName��passwordҲ���Ķ�html�����֪�ģ���Ϊ���ж�Ӧ�Ĳ�������
		out.write("TextBox1=3110101547&TextBox2=ayst314159&TextBox3=11111"); // post�Ĺؼ����ڣ�
		// remember to clean up
		out.flush();
		out.close();

		// ȡ��cookie���൱�ڼ�¼����ݣ����´η���ʱʹ��
		String cookieVal = connection.getHeaderField("Set-Cookie");
		System.out.println(cookieVal);

		String s = "http://jwbinfosys.zju.edu.cn/xskbcx.aspx?xh=3110101547&xn=2013-2014&xq=1";
		// ���´�һ������
		url = new URL(s);
		HttpURLConnection resumeConnection = (HttpURLConnection) url
				.openConnection();
		if (cookieVal != null) {
			// ����cookie��Ϣ��ȥ���Ա����Լ�����ݣ�����ᱻ��Ϊû��Ȩ��
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
