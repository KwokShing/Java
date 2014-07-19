package test.lgh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
//此部分仅供参考如需要，要加httpclient-commons包
//public class CustomGetMethod extends GetMethod {

//	public CustomGetMethod(String uri) {
//
//		super(uri);
//
//	}
//	@Override
//	public String getResponseBodyAsString() throws IOException {
//
//		GZIPInputStream gzin;
//
//		if (getResponseBody() != null || getResponseStream() != null) {
//			//获取压缩方式
//			if (getResponseHeader("Content-Encoding") != null && getResponseHeader("Content-Encoding").getValue().toLowerCase().indexOf("gzip") > -1) {
//				// For GZip response
//				InputStream is = getResponseBodyAsStream();
//				gzin = new GZIPInputStream(is);
//				InputStreamReader isr = new InputStreamReader(gzin,getResponseCharSet());
//				BufferedReader br = new BufferedReader(isr);
//				StringBuffer sb = new StringBuffer();
//				String tempbf;
//				while ((tempbf = br.readLine()) != null) {
//					sb.append(tempbf);
//					sb.append("\r\n");//回车换行
//				}
//				isr.close();
//				gzin.close();
//				return sb.toString();
//			} else {
//				return super.getResponseBodyAsString();
//			}
//		} else {
//			return null;
//		}
//	}
//}