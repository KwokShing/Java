package browser;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.*;
import org.apache.commons.httpclient.methods.*;

public class FormLoginDemo {
	static final String LOGON_SITE = "http://login.goodjobs.cn/index.php/action/UserLogin";
	static final int LOGON_PORT = 8080;
	public static void main(String[] args) throws Exception {
		HttpClient client = new HttpClient();
		client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT);
		// 模拟登录页面login.jsp->main.jsp
		PostMethod post = new PostMethod("http://login.goodjobs.cn/index.php/action/UserLogin");
		NameValuePair name = new NameValuePair("name", "ld");
		NameValuePair pass = new NameValuePair("password", "ld");
		post.setRequestBody(new NameValuePair[] { name, pass });
		int status = client.executeMethod(post);
		System.out.println(post.getResponseBodyAsString());
		post.releaseConnection();
		
		// 查看cookie信息
		CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
		Cookie[] cookies = cookiespec.match(LOGON_SITE, LOGON_PORT, "/", false,
				client.getState().getCookies());
		if (cookies.length == 0) {
			System.out.println("None");
		} else {
			for (int i = 0; i < cookies.length; i++) {
				System.out.println(cookies[i].toString());
			}
		}
		
		// 访问所需的页面main2.jsp
		GetMethod get = new GetMethod("http://login.goodjobs.cn/index.php/action/UserLogin");
		client.executeMethod(get);
		System.out.println(get.getResponseBodyAsString());
		get.releaseConnection();
	}
}
