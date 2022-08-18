package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.helper.DBHelper;
import com.mvc.bean.DistributoreAutomaticoBean;
import com.mvc.bean.UtenteBean;

public class DistributoreAutomaticoDAO {
	private Connection conn;
	
	public DistributoreAutomaticoDAO() {
		try {
			conn = DBHelper.connectToDB();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getStato(int idDistributore) {
		return null;
	}

	public static boolean registraDistributoreAutomatico(DistributoreAutomaticoBean registrazioneDistributoreAutomatico) {
		String locazione = registrazioneDistributoreAutomatico.getLocazione();
		
		try {
			Connection connection = DBHelper.connectToDB();
			
			PreparedStatement pstmt = null;
			pstmt = connection.prepareStatement("INSERT INTO DistributoreAutomatico (locazione) VALUES (?)");
			pstmt.setString(1, locazione);
			int result = pstmt.executeUpdate();
			
			pstmt.close();
			connection.close();
			
			return result > 0;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}