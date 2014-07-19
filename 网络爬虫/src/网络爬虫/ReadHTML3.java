package 网络爬虫;

import java.io.*;
import java.net.*;

class ReadHTML3 {
	String url;

	ReadHTML3(String u) {
		url = u;
	}

	public String gethtml() {
		String str = "";
		int i, j;
		try {
			URL sourceURL = new URL(url);
			// Get a character input stream for the URL
			BufferedReader in = new BufferedReader(new InputStreamReader(
					sourceURL.openStream()));

			String buf; // Buffer to store lines
			while (!(null == (buf = in.readLine()))) {
				if ((i = buf.indexOf("<")) >= 0 && (j = buf.indexOf(">")) < 0) {// 有些标签的开和闭并不是在同一行
					while (true) {
						buf += in.readLine();
						if ((j = buf.indexOf(">")) >= 0)
							break;
					}
				}
				if ((i = buf.indexOf("Copyright")) >= 0) {// 去除一些特定的字符串
					while (true) {
						buf += in.readLine();
						if ((buf.indexOf("width=\"68\">")) >= 0) {
							buf = buf.trim().replaceAll(
									"Copyright*width=\"68\">", "");
							break;
						}
					}
				}
				buf = buf.trim().replaceAll("<[^>]*>", "");// 正则表达式的使用
				str += (buf.equalsIgnoreCase("")) ? "" : "\r\n" + buf + "\r\n";
			}
			in.close(); // Close the input stream
		} catch (Exception e) {
			System.out.println("File error:\n" + e);
		}
		return str;
	}
}
