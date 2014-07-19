package ��������;

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
				if ((i = buf.indexOf("<font size=\"4\">")) >= 0) {// ������
					if (buf.indexOf("&nbsp;") >= 0) {// ���е���ʮ�ĺ���ʮ���²����³��ƣ��м�ֿ����У����⴦��
						buf += in.readLine().trim();
						buf = buf.replaceAll("&nbsp;", " ");
					}
					j = buf.indexOf("</font>", i);
					str += buf.substring(i + 15, j);
					str += "\r\n";
					while (!(null == (buf = in.readLine()))) { // �����ݣ�����<BR><BR>��ǩʶ��
						if ((l = buf.indexOf("</DIV>")) >= 0) {
							m = buf.indexOf("<BR><BR>", l);
							str += "\r\n" + buf.substring(l + 6, m).trim() + "\r\n";
						} 
						else if ((m = buf.indexOf("<BR><BR>")) >= 0) {// �����ݣ�����<BR><BR>��ǩʶ��
							str += "\r\n" + buf.substring(0, m).trim() + "\r\n";
						} 
						else if ((i = buf.indexOf("<")) < 0&& (j = buf.indexOf(">")) < 0) {
							buf += in.readLine();
							if ((i = buf.indexOf("<center>")) >= 0) {// ÿ�����һ�ε�����û��<BR><BR>��ǩ��ֻ����һ�е�<center>
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
