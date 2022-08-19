package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.helper.DBHelper;
import com.mvc.bean.AmministratoreBean;

public class AmministratoreDAO {

	public static boolean accediAmministratore(AmministratoreBean accessoAmministratore) throws SQLException {
		String email = accessoAmministratore.getEmail();
		String password = accessoAmministratore.getPassword();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		boolean check = false;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("SELECT * FROM Amministratore WHERE email = ? AND password = ?");
			pstmt.setString(1, email);
			pstmt.setString(2, DBHelper.getSHA512(password));
			result = pstmt.executeQuery();

			if(result.next()) {
				accessoAmministratore.setIdAmministratore(result.getInt("idAmministratore"));
				accessoAmministratore.setNome(result.getString("nome"));
				accessoAmministratore.setCognome(result.getString("cognome"));
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
}