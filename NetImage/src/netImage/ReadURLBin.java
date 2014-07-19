package netImage;

/*
 ReadURLBin.java：基本网络二进制读入并写入指定文件。
 以命令行选择URL参数。
 */
import java.io.*;
import java.net.*;

class ReadURLBin {
	String urlstr;

	ReadURLBin(String u) {
		urlstr = u;
	}

	void save() {
		String filename = urlstr.substring(urlstr.lastIndexOf("/") + 1);

		try {
			URL url = new URL(urlstr);

			// Get a character input stream for the URL
			DataInputStream in = new DataInputStream(new BufferedInputStream(
					url.openStream()));

			// Create the stream for the output file
			DataOutputStream out = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream(new File(
							filename))));

			// Read the URL and write it to a file
			try {
				while (true)
					out.writeByte(in.readByte());
			} catch (EOFException e) {
				System.out.println(filename + "\t*** End! ***\n\n");
				in.close(); // Close the input stream
				out.close(); // Close the output file
			}
		} catch (Exception e) {
			System.out.println("IOException:\n" + e);
		}
	}

	public static void main(String[] args) {
		ReadURLBin w = new ReadURLBin("http://www.baidu.com/img/shouye_b5486898c692066bd2cbaeda86d74448.gif");
		w.save();
	}
}
