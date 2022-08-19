package com.mvc.dao;

import com.mvc.bean.UtenteBean;
import com.helper.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAO {
	
	public static boolean registraUtente(UtenteBean registrazioneUtente) throws SQLException {
		String nome = registrazioneUtente.getNome();
		String cognome = registrazioneUtente.getCognome();
		String email = registrazioneUtente.getEmail();
		String password = registrazioneUtente.getPassword();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("INSERT INTO Utente (nome, cognome, email, password) values (?, ?, ?, ?)");
			pstmt.setString(1, nome);
			pstmt.setString(2, cognome);
			pstmt.setString(3, email);
			pstmt.setString(4, DBHelper.getSHA512(password));
			int result = pstmt.executeUpdate();
			
			return result > 0;		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connection.close();
		}
		
		return false; 
	}
	
	public static boolean accediUtente(UtenteBean accessoUtente) throws SQLException {
		String email = accessoUtente.getEmail();
		String password = accessoUtente.getPassword();
		
		ResultSet result = null;
		boolean check = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DBHelper.connectToDB();
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
				e.printStackTrace();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connection.close();
		}
		
		return check;
	}

	public static boolean ricaricaCredito(UtenteBean utente, int centesimiRicarica) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("UPDATE Utente SET centesimiCredito = ? WHERE idUtente = ?");
			pstmt.setInt(1, utente.getCentesimiCredito() + centesimiRicarica);
			pstmt.setInt(2, utente.getIdUtente());
			int result = pstmt.executeUpdate();
			
			return result > 0;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connection.close();
		}
		
		return false;
	}
}
