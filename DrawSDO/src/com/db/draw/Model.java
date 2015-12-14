package com.db.draw;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import oracle.jdbc.OracleResultSet;
import oracle.spatial.geometry.JGeometry;

public class Model {
	private final static String URL = "jdbc:oracle:thin:@192.168.56.101:1521:XE";
	private final static String USERNAME = "kross";
	private final static String PASSWORD = "314159";

	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	private HashMap<String, double[]> geoLionMap = new HashMap<String, double[]>();
	private HashMap<String, double[]> geoRegionMap = new HashMap<String, double[]>();
	private HashMap<String, double[]> geoPondMap = new HashMap<String, double[]>();

	public void connectDB() throws SQLException {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = con.createStatement();
			writeCoordinate("Lion", stmt);
			writeCoordinate("Region", stmt);
			writeCoordinate("Pond", stmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean state() {
		return con == null;
	}
	
	public void closeDB() {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeCoordinate(String type, Statement stmt) {
		try {
			rs = stmt.executeQuery("select * from " + type);
			while (rs.next()) {
				String id = new String(((OracleResultSet) rs).getBytes("id"));
				byte[] point = ((OracleResultSet) rs).getBytes("shape");
				JGeometry geoPoint = JGeometry.load(point);
				double[] dCoordiante;
				if (type.equals("Lion"))
					dCoordiante = geoPoint.getPoint();
				else
					dCoordiante = geoPoint.getOrdinatesArray();
				if (type.equals("Lion"))
					setGeoLion(id, dCoordiante);
				else if (type.equals("Pond"))
					setGeoPond(id, dCoordiante);
				else if (type.equals("Region"))
					setGeoRegion(id, dCoordiante);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setGeoLion(String id, double[] coordinate) {
		this.geoLionMap.put(id, coordinate);
	}

	public void setGeoRegion(String id, double[] coordinate) {
		this.geoRegionMap.put(id, coordinate);
	}

	public void setGeoPond(String id, double[] coordinate) {
		this.geoPondMap.put(id, coordinate);
	}

	public HashMap<String, double[]> getGeoLionMap() {
		return geoLionMap;
	}

	public HashMap<String, double[]> getGeoRegionMap() {
		return geoRegionMap;
	}

	public HashMap<String, double[]> getGeoPondMap() {
		return geoPondMap;
	}

	public ArrayList<double[]> sendMousePosition(int x, int y, String type) {
		ArrayList<double[]> newLionPos = new ArrayList<double[]>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(formatQuery(x, y, type));
			while (rs.next()) {
				byte[] point = ((OracleResultSet) rs).getBytes("shape");
				JGeometry geoPoint = JGeometry.load(point);
				if (type.equals("Lion"))
					newLionPos.add(geoPoint.getPoint());
				else if (type.equals("Pond"))
					newLionPos.add(geoPoint.getOrdinatesArray());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newLionPos;
	}

	public String formatQuery(int x, int y, String type) {
		return String
				.format("select l.* from " + "(select * from Region r " + "where SDO_ANYINTERACT(r.shape,SDO_GEOMETRY"
						+ "(2001, NULL, SDO_POINT_TYPE(%d, %d, NULL), NULL, NULL)) = 'TRUE') s, %s l "
						+ "where SDO_ANYINTERACT(l.shape,s.shape) = 'TRUE'", x, y, type);
	}
}