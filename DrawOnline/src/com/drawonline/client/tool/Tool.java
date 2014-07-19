package com.drawonline.client.tool;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.drawonline.client.bean.Kind;

/**
 * xxx
 * 
 * @author lw
 * 
 */
public class Tool {
	
	public static List<Kind> getAllKinds() {
		List<Kind> listResult = new ArrayList<Kind>();
		String relativelyPath = System.getProperty("user.dir");
		File file = new File(relativelyPath + "//str.txt");

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String readLine = "";
			while ((readLine = reader.readLine()) != null) {
				String keyStr = readLine.split(":")[0];
				String valueStr = readLine.split(":")[1];
				String kindTypeStr[] = valueStr.split(";");
				for(int i = 0 ; i < kindTypeStr.length ; i++){
					Kind kind  = new Kind();
					String kindStr[] = kindTypeStr[i].split("-");
					int id = Integer.parseInt(kindStr[0]);
					String name = kindStr[1];
					kind.setId(id);
					kind.setName(name);
					kind.setType(keyStr);
					listResult.add(kind);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}
}
