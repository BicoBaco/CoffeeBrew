package com.mvc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//TODO insert distributore
	
	public UtenteBean getOccupante(int idDistributore) {
		try {
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement("SELECT nome, centesimiCredito FROM DistributoreAutomatico, Utente WHERE idUtente = occupante AND idDistributore = ?");
			pstmt.setInt(1, idDistributore);
			ResultSet rs = pstmt.executeQuery();
			UtenteBean occupante = new UtenteBean();
			
			if(rs.next()) {
				occupante.setNome(rs.getString("nome"));
				occupante.setCentesimiCredito(rs.getInt("centesimiCredito"));
			} else {
				occupante = null;
			}
			pstmt.close();
			
			return occupante;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void impostaOccupato(int idDistributore, int idUtente) {
		try {
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement("UPDATE DistributoreAutomatico SET occupante = ? WHERE idDistributore = ?");
			pstmt.setInt(1, idUtente);
			pstmt.setInt(2, idDistributore);
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void impostaLibero(int idDistributore) {
		try {
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement("UPDATE DistributoreAutomatico SET occupante = NULL WHERE idDistributore = ?");
			pstmt.setInt(1, idDistributore);
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
