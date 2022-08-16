package com.mvc.dao;

import com.mvc.bean.UtenteBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAO {
	
	public static boolean registraUtente(UtenteBean registrazioneUtente) {
		String nome = registrazioneUtente.getNome();
		String cognome = registrazioneUtente.getCognome();
		String email = registrazioneUtente.getEmail();
		String password = registrazioneUtente.getPassword();
		
		String DBurl = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11512766";
		String DBusername = "sql11512766";
		String DBpassword = "z9114hAzpB";
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
				
			Connection connection = DriverManager.getConnection(DBurl, DBusername, DBpassword);
			
			PreparedStatement pstmt = null;
			pstmt = connection.prepareStatement("INSERT INTO Utente (nome, cognome, email, password) values (?, ?, ?, ?)");
			pstmt.setString(1, nome);
			pstmt.setString(2, cognome);
			pstmt.setString(3, email);
			pstmt.setString(4, password);
			int result = pstmt.executeUpdate();
			
			pstmt.close();
			connection.close();
			
			return result > 0;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean accediUtente(UtenteBean accessoUtente) {
		String email = accessoUtente.getEmail();
		String password = accessoUtente.getPassword();
				
		String DBurl = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11512766";
		String DBusername = "sql11512766";
		String DBpassword = "z9114hAzpB";
		
		ResultSet result = null;
		boolean check = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
				
			Connection connection = DriverManager.getConnection(DBurl, DBusername, DBpassword);
			
			PreparedStatement pstmt = null;
			pstmt = connection.prepareStatement("SELECT * FROM Utente WHERE email = ? AND password = ?");
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			result = pstmt.executeQuery();

			
			try {
				if(result.next()) {
					accessoUtente.setNome(result.getString("nome"));
					accessoUtente.setCognome(result.getString("cognome"));
					check = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pstmt.close();
			connection.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return check;
	}
}
