package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.helper.DBHelper;
import com.mvc.bean.ProdottoBean;

public class ProdottoDAO {
	public static void aggiornaStatistica(int idProdotto) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("UPDATE Prodotto SET qtaVendute = qtaVendute + 1 WHERE idProdotto = ?");
			pstmt.setInt(1, idProdotto);
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connection.close();
		}
		
	}
	
	public static ArrayList<ProdottoBean> getProdotti() throws SQLException {
		ArrayList<ProdottoBean> listaProdotti = new ArrayList<ProdottoBean>();
		
		Connection connection = null;	
		PreparedStatement pstmt = null;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("SELECT * FROM Prodotto");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProdottoBean prodotto = new ProdottoBean();
				prodotto.setIdProdotto(rs.getInt("idProdotto"));
				prodotto.setNome(rs.getString("nome"));
				prodotto.setCosto(rs.getInt("costo"));
				prodotto.setQtaVendute(rs.getInt("qtaVendute"));
				listaProdotti.add(prodotto);
			}

			return listaProdotti;			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connection.close();
		}
		
		return null;
	}

	public static void inserisciProdotto(ProdottoBean prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("INSERT INTO Prodotto (nome, costo, qtaVendute) VALUES (?, ?, 0)");
			pstmt.setString(1, prodotto.getNome());
			pstmt.setInt(2, prodotto.getCosto());
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connection.close();
		}
	}
	
	public static boolean rimuoviProdotto(int idProdotto) throws SQLException {
		Connection connection = null;	
		PreparedStatement pstmt = null;
		int result;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("DELETE FROM Prodotto WHERE idProdotto = ?");
			pstmt.setInt(1, idProdotto);
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