package com.mvc.dao;

import com.mvc.bean.UtenteBean;
import com.helper.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class UtenteDAO {
	
	public static boolean registraUtente(UtenteBean registrazioneUtente) throws SQLException, SQLIntegrityConstraintViolationException {
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
			if(e instanceof SQLIntegrityConstraintViolationException) throw new SQLIntegrityConstraintViolationException();
			if(e instanceof SQLException) throw new SQLException();
			e.printStackTrace();
		}finally {
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

	public static boolean rimuoviCredito(UtenteBean utente, int centesimiImporto) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("UPDATE Utente SET centesimiCredito = centesimiCredito - ? WHERE idUtente = ?");
			pstmt.setInt(1, centesimiImporto);
			pstmt.setInt(2, utente.getIdUtente());
			boolean result = pstmt.execute();
			
			return result;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connection.close();
		}
		
		return false;
	}
	
	public static ArrayList<UtenteBean> getUtenti() throws SQLException {
		ArrayList<UtenteBean> listaUtenti = new ArrayList<UtenteBean>();
		
		Connection connection = null;	
		PreparedStatement pstmt = null;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("SELECT * FROM Utente");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UtenteBean utente = new UtenteBean();
				utente.setIdUtente(rs.getInt("idUtente"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setEmail(rs.getString("email"));
				listaUtenti.add(utente);
			}

			return listaUtenti;			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connection.close();
		}
		
		return null;
	}
	
	public static boolean rimuoviUtente(int idUtente) throws SQLException {
		Connection connection = null;	
		PreparedStatement pstmt = null;
		int result;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("DELETE FROM Utente WHERE idUtente = ?");
			pstmt.setInt(1, idUtente);
			result = pstmt.executeUpdate();
			
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
