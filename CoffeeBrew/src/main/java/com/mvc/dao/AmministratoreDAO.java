package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.helper.DBHelper;
import com.mvc.bean.AmministratoreBean;

public class AmministratoreDAO {

	public static boolean accediAmministratore(AmministratoreBean accessoAmministratore) {
		String email = accessoAmministratore.getEmail();
		String password = accessoAmministratore.getPassword();

		
		ResultSet result = null;
		boolean check = false;
		
		try {
			Connection connection = DBHelper.connectToDB();
			
			PreparedStatement pstmt = null;
			pstmt = connection.prepareStatement("SELECT * FROM Amministratore WHERE email = ? AND password = ?");
			pstmt.setString(1, email);
			pstmt.setString(2, DBHelper.getSHA512(password));
			result = pstmt.executeQuery();

			try {
				if(result.next()) {
					accessoAmministratore.setIdAmministratore(result.getInt("idAmministratore"));
					accessoAmministratore.setNome(result.getString("nome"));
					accessoAmministratore.setCognome(result.getString("cognome"));
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