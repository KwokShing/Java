package Õ¯¬Á≈¿≥Ê;

import java.io.*;
import java.net.*;

class ReadURL {
	public static void main(String[] args) {
		try {
			URL sourceURL = // Define a URL
			new URL("http://ds.eywedu.com/jinyong/tlbb/");
			// Get a character input stream for the URL
			BufferedReader in = new BufferedReader(new InputStreamReader(
					sourceURL.openStream()));

			// Create the stream for the output file
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(new File("tlbb.txt"))));

			int i, j;
			String u = "http://ds.eywedu.com/jinyong/tlbb/";
			String buf, str; // Buffer to store lines

			while (!(null == (buf = in.readLine()))) {
				if ((i = buf.indexOf("<A HREF=\"mydoc")) >= 0) {
					j = buf.indexOf(".htm\"", i);

					str = u + buf.substring(i + 9, j + 4);
					System.out.println(str);

					out.println(new ReadHTML(str).gethtml());
				}
			}

			in.close(); // Close the input stream
			out.close(); // Close the input stream
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
