package com.mvc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.helper.DBHelper;
import com.mvc.bean.CartaDiCreditoBean;

public class CartaDiCreditoDAO {
	public static boolean inserisciCarta(CartaDiCreditoBean cartaDiCredito) throws SQLException {
		String numeroCarta = cartaDiCredito.getNumeroCarta();
		String nomeSullaCarta = cartaDiCredito.getNomeSullaCarta();
		Date dataScadenza = cartaDiCredito.getDataScadenza();
		int idUtente = cartaDiCredito.getIdUtente();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = null;
			pstmt = connection.prepareStatement("INSERT INTO CartaDiCredito (numeroCarta, nomeSullaCarta, dataScadenza, idUtente) values (?, ?, ?, ?)");
			pstmt.setString(1, numeroCarta);
			pstmt.setString(2, nomeSullaCarta);
			pstmt.setDate(3, dataScadenza);
			pstmt.setInt(4, idUtente);			
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
	
	public static ArrayList<CartaDiCreditoBean> getCarte(int idUtente) throws SQLException {
		ArrayList<CartaDiCreditoBean> listaCarte = new ArrayList<CartaDiCreditoBean>();
		
		Connection connection = null;	
		PreparedStatement pstmt = null;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("SELECT * FROM CartaDiCredito WHERE idUtente = ?");
			pstmt.setInt(1, idUtente);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartaDiCreditoBean cdc = new CartaDiCreditoBean();
				cdc.setIdCarta(rs.getInt("idCarta"));
				cdc.setNumeroCarta("****-****-****-"+rs.getString("numeroCarta").substring(12));
				cdc.setNomeSullaCarta(rs.getString("nomeSullaCarta"));
				cdc.setDataScadenza(rs.getDate("dataScadenza"));
				
				listaCarte.add(cdc);
			}
			
			return listaCarte;			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connection.close();
		}
		
		return null;
	}
	
	public static boolean rimuoviCarta(int idCarta) throws SQLException {
		Connection connection = null;	
		PreparedStatement pstmt = null;
		int result;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("DELETE FROM CartaDiCredito WHERE idCarta = ?");
			pstmt.setInt(1, idCarta);
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