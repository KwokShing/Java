package 网络爬虫;

import java.io.*;
import java.net.*;

class ReadHTML {
	String url;

	ReadHTML(String u) {
		url = u;
	}

	public String gethtml() {
		String str = "";
		int i, j, l, m;
		try {
			URL sourceURL = new URL(url);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					sourceURL.openStream()));

			String buf; // Buffer to store lines

			while (!(null == (buf = in.readLine()))) {
				if ((i = buf.indexOf("<font size=\"4\">")) >= 0) {// 读标题
					if (buf.indexOf("&nbsp;") >= 0) {// 其中第四十四和四十六章不按章出牌，中间分开两行，特殊处理
						buf += in.readLine().trim();
						buf = buf.replaceAll("&nbsp;", " ");
					}
					j = buf.indexOf("</font>", i);
					str += buf.substring(i + 15, j);
					str += "\r\n";
					while (!(null == (buf = in.readLine()))) { // 读内容，根据<BR><BR>标签识别
						if ((l = buf.indexOf("</DIV>")) >= 0) {
							m = buf.indexOf("<BR><BR>", l);
							str += "\r\n" + buf.substring(l + 6, m).trim() + "\r\n";
						} 
						else if ((m = buf.indexOf("<BR><BR>")) >= 0) {// 读内容，根据<BR><BR>标签识别
							str += "\r\n" + buf.substring(0, m).trim() + "\r\n";
						} 
						else if ((i = buf.indexOf("<")) < 0&& (j = buf.indexOf(">")) < 0) {
							buf += in.readLine();
							if ((i = buf.indexOf("<center>")) >= 0) {// 每章最后一段的文字没有<BR><BR>标签，只有下一行的<center>
								str += "\r\n" + buf.substring(0, i).trim() + "\r\n";
							}
						}
					}
				}
			}
			in.close(); // Close the input stream
		} catch (Exception e) {
			System.out.println("File error:\n" + e);
		}
		return str;
	}
}
