package com.zlc.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 用户数据库类，用于连接储存用户名和密码信息的Access数据库。 数据库使用JDBC::ODBC连接ODBC数据源
 * 
 * @author 赵景晨
 * 
 */
public class UserDB {
	/** 数据库连接 */
	private Connection conn;
	/** 向数据库发送SQL语句的statement对象 */
	private Statement stmt;

	/**
	 * 无参构造函数，连接用户数据库
	 */
	public UserDB() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn = DriverManager.getConnection("jdbc:odbc:UserDB");
			System.out.println("用户数据库已连接！");
			stmt = conn.createStatement();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 通过用户名查询密码
	 * 
	 * @param userName
	 *            要查找的用户名
	 * @return 该用户的密码，如果用户名未找到则返回null
	 */
	public String selectPassWowdByName(String userName) {
		try {
			ResultSet result = stmt
					.executeQuery("select passWord from user where userName='"
							+ userName + "'");
			if (result.next() == false) {// 未找到该用户
				return null;
			} else {
				String passWord = result.getString(1);
				return passWord;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}
	}
}
