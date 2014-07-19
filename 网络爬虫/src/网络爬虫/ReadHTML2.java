package ÍøÂçÅÀ³æ;

import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ReadHTML2 {
	String url;

	ReadHTML2(String u) {
		url = u;
	}

	public String gethtml() {
		String str = "";
		int i, j, k, l, m;
		String Regex;
		// Regex="[µÚ*ÕÂ]";
		Regex = "<([^>]*)>";

		// Pattern pattern = Pattern.compile(Regex);
		StringBuffer sb = new StringBuffer();
		try {
			URL sourceURL = new URL(url);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					sourceURL.openStream()));

			String buf; // Buffer to store lines
			/*
			 * while (!(null == (buf = in.readLine()))) { Matcher matcher =
			 * pattern.matcher(buf); if (matcher.find()) {
			 * matcher.appendReplacement(sb, ""); // result1 = matcher.find();
			 * // System.out.println(matcher.group()); } }
			 */
			while (!(null == (buf = in.readLine()))) {
				Pattern pattern = Pattern.compile(Regex);
				Matcher matcher = pattern.matcher(buf);

				boolean result1 = matcher.find();
				while (result1) {
					matcher.appendReplacement(sb, "");
					result1 = matcher.find();
				}
				matcher.appendTail(sb);
				// return sb.toString();
			}
			in.close(); // Close the input stream
		} catch (Exception e) {
			System.out.println("File error:\n" + e);
		}
		// return str;
		return sb.toString();
	}
}
