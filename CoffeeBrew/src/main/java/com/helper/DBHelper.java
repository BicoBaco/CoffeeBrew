package com.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
	private static String DBurl = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11512766";
	private static String DBusername = "sql11512766";
	private static String DBpassword = "z9114hAzpB";
	
	public static Connection connectToDB() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		return DriverManager.getConnection(DBurl, DBusername, DBpassword);
	}
}
