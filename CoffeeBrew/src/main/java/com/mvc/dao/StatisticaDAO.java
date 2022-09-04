package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.helper.DBHelper;

public class StatisticaDAO {
	public static void aggiornaStatistica(String tipoProdotto) throws SQLException{
		Connection connection = null;
		PreparedStatement pstmtCheck = null;
		PreparedStatement pstmtUpdate = null;
		PreparedStatement pstmtAdd = null;
		ResultSet rs = null;
		
		try {
			connection = DBHelper.connectToDB();
			pstmtCheck = connection.prepareStatement("SELECT idStatistica FROM Statistica WHERE tipoProdotto = ?");
			pstmtCheck.setString(1, tipoProdotto);
			rs = pstmtCheck.executeQuery();
			
			if(rs.next()) {
				pstmtUpdate = connection.prepareStatement("UPDATE Statistica SET qtaVendute = qtaVendute + 1 WHERE tipoProdotto = ?");
				pstmtUpdate.setString(1, tipoProdotto);
				pstmtUpdate.executeUpdate();
				pstmtUpdate.close();
			} else {
				pstmtAdd = connection.prepareStatement("INSERT INTO Statistica (tipoProdotto, qtaVendute) VALUES (?, 1)");
				pstmtAdd.setString(1, tipoProdotto);
				pstmtAdd.executeUpdate();
				pstmtAdd.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pstmtCheck.close();
			connection.close();
		}
	}
}
