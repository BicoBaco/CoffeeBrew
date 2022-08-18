package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.helper.DBHelper;
import com.mvc.bean.TecnicoBean;

public class TecnicoDAO {
	
	public static boolean registraTecnico(TecnicoBean registrazioneTecnico) {
		String nome = registrazioneTecnico.getNome();
		String cognome = registrazioneTecnico.getCognome();
		String email = registrazioneTecnico.getEmail();
		String password = registrazioneTecnico.getPassword();
		
		try {
			Connection connection = DBHelper.connectToDB();
			
			PreparedStatement pstmt = null;
			pstmt = connection.prepareStatement("INSERT INTO Tecnico (nome, cognome, email, password) values (?, ?, ?, ?)");
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
	
	public static boolean accediTecnico(TecnicoBean accessoTecnico) {
		String email = accessoTecnico.getEmail();
		String password = accessoTecnico.getPassword();

		
		ResultSet result = null;
		boolean check = false;
		
		try {
			Connection connection = DBHelper.connectToDB();
			
			PreparedStatement pstmt = null;
			pstmt = connection.prepareStatement("SELECT * FROM Tecnico WHERE email = ? AND password = ?");
			pstmt.setString(1, email);
			pstmt.setString(2, DBHelper.getSHA512(password));
			result = pstmt.executeQuery();

			try {
				if(result.next()) {
					accessoTecnico.setIdTecnico(result.getInt("idTecnico"));
					accessoTecnico.setNome(result.getString("nome"));
					accessoTecnico.setCognome(result.getString("cognome"));
					check = true;
				}
			} catch (SQLException e) {
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
