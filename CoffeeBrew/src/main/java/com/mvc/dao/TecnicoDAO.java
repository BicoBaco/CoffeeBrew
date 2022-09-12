package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import com.helper.DBHelper;
import com.mvc.bean.TecnicoBean;

public class TecnicoDAO {
	
	public static boolean registraTecnico(TecnicoBean registrazioneTecnico) throws SQLException, SQLIntegrityConstraintViolationException {
		String nome = registrazioneTecnico.getNome();
		String cognome = registrazioneTecnico.getCognome();
		String email = registrazioneTecnico.getEmail();
		String password = registrazioneTecnico.getPassword();
		
		Connection connection = null; 
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DBHelper.connectToDB();		
			pstmt = connection.prepareStatement("INSERT INTO Tecnico (nome, cognome, email, password) values (?, ?, ?, ?)");
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
		} finally {
			pstmt.close();
			connection.close();
		}
		
		return false; 
	}
	
	public static boolean accediTecnico(TecnicoBean accessoTecnico) throws SQLException {
		String email = accessoTecnico.getEmail();
		String password = accessoTecnico.getPassword();

		
		ResultSet result = null;
		boolean check = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("SELECT * FROM Tecnico WHERE email = ? AND password = ?");
			pstmt.setString(1, email);
			pstmt.setString(2, DBHelper.getSHA512(password));
			result = pstmt.executeQuery();


			if(result.next()) {
				accessoTecnico.setIdTecnico(result.getInt("idTecnico"));
				accessoTecnico.setNome(result.getString("nome"));
				accessoTecnico.setCognome(result.getString("cognome"));
				check = true;
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connection.close();
		}
		
		return check;
	}
	
	public static ArrayList<TecnicoBean> getTecnici() throws SQLException {
		ArrayList<TecnicoBean> listaTecnici = new ArrayList<TecnicoBean>();
		
		Connection connection = null;	
		PreparedStatement pstmt = null;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("SELECT * FROM Tecnico");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TecnicoBean tecnico = new TecnicoBean();
				tecnico.setIdTecnico(rs.getInt("idTecnico"));
				tecnico.setNome(rs.getString("nome"));
				tecnico.setCognome(rs.getString("cognome"));
				tecnico.setEmail(rs.getString("email"));
				listaTecnici.add(tecnico);
			}

			return listaTecnici;			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connection.close();
		}
		
		return null;
	}
	
	public static boolean rimuoviTecnico(int idTecnico) throws SQLException {
		Connection connection = null;	
		PreparedStatement pstmt = null;
		int result;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("DELETE FROM Tecnico WHERE idTecnico = ?");
			pstmt.setInt(1, idTecnico);
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
