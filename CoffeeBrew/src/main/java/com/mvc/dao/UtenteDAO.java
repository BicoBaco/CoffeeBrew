package com.mvc.dao;

import com.mvc.bean.UtenteBean;
import com.helper.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAO {
	
	public static boolean registraUtente(UtenteBean registrazioneUtente) {
		String nome = registrazioneUtente.getNome();
		String cognome = registrazioneUtente.getCognome();
		String email = registrazioneUtente.getEmail();
		String password = registrazioneUtente.getPassword();
		
		try {
			Connection connection = DBHelper.connectToDB();
			
			PreparedStatement pstmt = null;
			pstmt = connection.prepareStatement("INSERT INTO Utente (nome, cognome, email, password) values (?, ?, ?, ?)");
			pstmt.setString(1, nome);
			pstmt.setString(2, cognome);
			pstmt.setString(3, email);
			pstmt.setString(4, DBHelper.getSHA512(password));
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

		
		ResultSet result = null;
		boolean check = false;
		
		try {
			Connection connection = DBHelper.connectToDB();
			
			PreparedStatement pstmt = null;
			pstmt = connection.prepareStatement("SELECT * FROM Utente WHERE email = ? AND password = ?");
			pstmt.setString(1, email);
			pstmt.setString(2, DBHelper.getSHA512(password));
			result = pstmt.executeQuery();

			try {
				if(result.next()) {
					accessoUtente.setIdUtente(result.getInt("idUtente"));
					accessoUtente.setNome(result.getString("nome"));
					accessoUtente.setCognome(result.getString("cognome"));
					accessoUtente.setCentesimiCredito(result.getInt("centesimiCredito"));
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
