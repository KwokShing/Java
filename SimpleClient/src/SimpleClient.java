import java.io.IOException;

import org.apache.commons.httpclient.*;

import org.apache.commons.httpclient.methods.*;

public class SimpleClient {

	public static void main(String[] args) throws IOException

	{

		HttpClient client = new HttpClient();

		// ���ô����������ַ�Ͷ˿�

		// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);

		// ʹ��GET�����������������Ҫͨ��HTTPS���ӣ���ֻ��Ҫ������URL�е�http����https

		HttpMethod method = new GetMethod("http://jwbinfosys.zju.edu.cn/xskbcx.aspx?xh=3110101547&xn=2013-2014&xq=1");

		// ʹ��POST����

		// HttpMethod method = new
		// PostMethod("http://www.oracle.com/index.html");

		client.executeMethod(method);

		// ��ӡ���������ص�״̬

		System.out.println(method.getStatusLine());

		// ��ӡ���ص���Ϣ

		System.out.println(method.getResponseBodyAsString());

		// �ͷ�����

		method.releaseConnection();

	}

}
