import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import com.spreada.utils.chinese.ZHConverter;

public class Testing {
	private static final String testPath = "C:/Users/Kross/Desktop/data/test/";
	private static final String res = "C:/Users/Kross/Desktop/data/res.csv";

	public Testing(Map<String, ArrayList<Integer>> mymap) {
		try {
			File resfile = new File(res);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(resfile/* , true */), "UTF8"));
			bw.write("Id" + "," + "Category" + "\r\n");

			for (int i = 0; i < 6000; i++) {
				File file = new File(testPath + i + ".txt");
				Scanner fileScan = new Scanner(new BufferedReader(
						new InputStreamReader(new FileInputStream(file))));
				Posterior p[] = new Posterior[6];
				for (int k = 0; k < 6; k++)
					p[k] = new Posterior(k);
				while (fileScan.hasNext()) {
					String tmp = fileScan.next();
					ZHConverter converter = ZHConverter
							.getInstance(ZHConverter.SIMPLIFIED);
					tmp = converter.convert(tmp);

					if (mymap.get(tmp) != null) {
						double total;
						// total = (double)
						// (mymap.get(/*tmp*/"TOTAL").get(/*6*/j) + 6 );
						for (int j = 0; j < 6; j++) {
							total = (double) (mymap.get("TOTAL").get(j)+mymap.get("DUPLICATE").get(0));
							p[j].posterior += Math
									.log((mymap.get(tmp).get(j) + 1) / total);
						}

					}
				}
				fileScan.close();
				// for(int ii=0;ii<6;ii++)
				// System.out.println(p[ii].id+" "+p[ii].posterior);
				Arrays.sort(p);
				// for(int ii=0;ii<6;ii++)
				// System.out.println(p[ii].id+" "+p[ii].posterior);

				System.out.println(i);
				bw.write(i + "," + p[0].id + "\r\n");
			}
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		Training train = new Training();
		new Testing(train.mymap);
	}
}
