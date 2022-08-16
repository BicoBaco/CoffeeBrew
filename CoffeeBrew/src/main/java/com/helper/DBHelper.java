package com.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	
	public static String getSHA512(String password) {
		
        String newPassword = null;
        
        try {
        	
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(password.getBytes());
            
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            newPassword = sb.toString();
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return newPassword;
    }    
}