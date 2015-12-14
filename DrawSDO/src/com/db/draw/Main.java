package com.db.draw;

import java.sql.SQLException;

import javax.swing.JFrame;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1638370856130086405L;
	private static Model model = new Model();
	
	public static void main(String[] args) throws SQLException {
		
		model.connectDB();
		new ViewController(model);
	}
}
