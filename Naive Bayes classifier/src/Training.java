import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.spreada.utils.chinese.ZHConverter;

public class Training {
	private static final String stopWord = "C:/Users/Kross/Desktop/data/stop.txt";
	private static final String rootPath = "C:/Users/Kross/Desktop/data/train/";
	private static final String testPath = "C:/Users/Kross/Desktop/data/test/";

	public Map<String, ArrayList<Integer>> mymap;

	public Training() throws Exception {

		Set<String> s = new HashSet<String>();
		mymap = new HashMap<String, ArrayList<Integer>>();
		ArrayList<Integer> l = new ArrayList<Integer>(7);
		ArrayList<Integer> d = new ArrayList<Integer>(7);
		for (int i = 0; i < 7; i++) {
			l.add(0);
			d.add(0);
		}
		mymap.put("TOTAL", l);
		mymap.put("DUPLICATE", d);

		try {
			File stopFile = new File(stopWord);
			Scanner stopScan = new Scanner(new BufferedReader(
					new InputStreamReader(new FileInputStream(stopFile))));
			while (stopScan.hasNextLine())
				s.add(stopScan.nextLine());
			stopScan.close();

			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 2000; j++) {
					File file = new File(rootPath + i + "/" + j + ".txt"/*
																		 * testPath
																		 * + j +
																		 * ".txt"
																		 */);
					Scanner fileScan = new Scanner(new BufferedReader(
							new InputStreamReader(new FileInputStream(file))));

					while (fileScan.hasNext()) {
						String tmp = fileScan.next();
						ZHConverter converter = ZHConverter
								.getInstance(ZHConverter.SIMPLIFIED);
						tmp = converter.convert(tmp);

						if (s.contains(tmp))
							continue;
						if (mymap.get(tmp) == null) {
							ArrayList<Integer> list = new ArrayList<Integer>(7);
							for (int k = 0; k < 7; k++)
								list.add(0);
							list.set(i, 1);
							list.add(6, 1);
							mymap.put(tmp, list);

							mymap.get("DUPLICATE").set(0,
									mymap.get("DUPLICATE").get(0) + 1);

						} else {
							mymap.get(tmp).set(i, mymap.get(tmp).get(i) + 1);
							mymap.get(tmp).set(6, mymap.get(tmp).get(6) + 1);

						}
						mymap.get("TOTAL")
								.set(i, mymap.get("TOTAL").get(i) + 1);
					}
					fileScan.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		File resfile = new File("C:/Users/Kross/Desktop/stop2.csv");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(resfile/* , true */), "GBK"));
		bw.write("Category,rank\r\n");

		for (int ii = 0; ii < 1; ii++) {
			ArrayList<Top10> top = new ArrayList<Top10>();
			for (String key : mymap.keySet()) {
				/* test */
//
//				bw.write(key);
//				double log[]=new double[6];
//				for (int i = 0; i < 6; i++) {
//					double cTotal = mymap.get("TOTAL").get(i);
//					double get = mymap.get(key).get(i);
//					log[i] = -Math.log((get + 1)
//							/ (cTotal + mymap.get("DUPLICATE").get(i)));
//					bw.write("," +  log[i]);
//					
//				}
//				Arrays.sort(log);
//				
//				bw.write("," + mymap.get(key).get(6));
//				bw.write(","+(int)(log[0]-log[5]));
//				bw.write("\r\n");

				if (key == "TOTAL" || key == "DUPLICATE")
					continue;

				double ratio[] = new double[6];

				for (int i = 0; i < 6; i++) {
					double cTotal = mymap.get("TOTAL").get(i)
							+ mymap.get("DUPLICATE").get(i);
					ratio[i] = (mymap.get(key).get(i) + 1) / cTotal;
				}
				double res = 0;

				for (int i = 0; i < 6; i++) {
					if (i == ii)
						continue;
					res += ratio[i];
				}
				res = ratio[ii] / res;
				top.add(new Top10(key, res));
			}
			Collections.sort(top);
			switch (ii) {
			case 0:
				bw.write("计算机科学与技术");
				break;
			case 1:
				bw.write("电脑数码");
				break;
			case 2:
				bw.write("彼岸阳光");
				break;
			case 3:
				bw.write("缘分天空 ");
				break;
			��� �c�   >貭                                                                                                                                                                                                                                                                                                                                                     