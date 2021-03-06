package test.lgh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class httpclient {
	GZIPInputStream gzin;
	static String userId = "948721187";
	static String serverId = "5";

	/* 登录消息包 */
	public static String post_8101(HttpClient httpclient) throws Exception {

		try {
			// 构造一个post对象//通过浏览器的开发人员工具了解
			HttpPost httpPost = new HttpPost(
					"http://127.0.0.1:8080/do?&sid=5&pid=1&mid=8101");

			// 添加所需要的post内容
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair(
					"data",
					"{    \"channel_key\" : \"0000010000\",    \"imei\" : \"imei\",    \"net_id\" : 1,    \"openid\" : \"\",    \"server_id\" : "
							+ serverId
							+ ",    \"sign\" : "
							+ userId
							+ ",    \"timestamp\" : \"\",    \"user_type\" : 0,    \"ver\" : 7623 } "));

			// 设置请求头
			httpPost.addHeader("Accept-Encoding", "gzip");
			// httpPost.addHeader("(Request-Line)","POST /do?sid=5&pid=43977182&mid=2012 HTTP/1.1");
			httpPost.addHeader("Accept", "*/*");
			httpPost.addHeader("Content-Type",
					"application/x-www-form-urlencoded");
			httpPost.addHeader("Host", "127.0.0.1:8080");
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
			// 执行请求
			HttpResponse response = httpclient.execute(httpPost);
			// 获取响应实例
			HttpEntity entity = response.getEntity();
			// String result = EntityUtils.toString(entity).substring(1);
			String str;
			// 响应内容放入gzip输入流
			GZIPInputStream gzip = new GZIPInputStream(entity.getContent());
			// gzip放入缓存中
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					gzip));
			// 创建一个构造器对象
			StringBuilder strb = new StringBuilder();
			// 一行一行读取内容，后关闭缓存流及gzip流（不然下面httpClient没释放，用有问题）
			while ((str = reader.readLine()) != null) {
				strb.append(str);
				// 正则式回车换行
				strb.append("\r\n");// 回车换行
			}
			reader.close();
			gzip.close();
			JSONObject jsonObject = null;
			try {
				// 获取返回的内容，转成对象
				jsonObject = new JSONObject(strb.substring(1));
			} catch (ParseException pe) {
				System.out.println(" ParseException:  " + pe.toString());
			}
			// 根据字段名（data),获取相应的内容，不同人定义的可能不同
			JSONObject data = (JSONObject) jsonObject.get("data");
			// 获取相应的内容，比如cookie,后传到下个方法使用
			return (String) data.get("T1");

		} finally {
			System.out.println("ok!");
		}

	}

	/* 排行榜 */
	public static void post_2602(String token, HttpClient httpclient)
			throws Exception {

		try {
			// 构造一个post对象
			HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/do?&sid="
					+ serverId + "&pid=" + userId + "&mid=2602");

			// 添加所需要的post内容
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair(
					"data",
					"{    \"loginKey\" : \""
							+ token
							+ "\",    \"pageNum\" : 1,    \"pageSize\" : 10,    \"playerRankType\" : 3 } "));

			httpPost.addHeader("Accept-Encoding", "gzip");
			httpPost.addHeader("Accept", "*/*");
			httpPost.addHeader("Content-Type",
					"application/x-www-form-urlencoded");
			httpPost.addHeader("Host", "127.0.0.1:8080");
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

			HttpResp[E:\resources]
errorlist=
fileid=
flag=-1
isfolder=1
path=E:\resources\computer\C++\C++ Primer code\14\README.TXT
pickcode=
quickid=
target=U_1_0
totalsize=2353562592
transfersize=406204179
[E:\me]
errorlist=
fileid=BE170AD8F8D47F6C387FFFEA97F3E0A87F59FA4A
flag=-1
isfolder=1
path=E:\me\相册\北京\100_2622.JPG
pickcode=53ca3043cd05b2fa65000022
quickid=D633019C1F2E334D3692DA3D077D832786465988
target=U_1_0
totalsize=1734145947
transfersize=80159334
[E:\Computer]
errorlist=
fileid=
flag=-1
isfolder=1
path=
pickcode=
quickid=
target=U_1_0
totalsize=0
transfersize=0
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            