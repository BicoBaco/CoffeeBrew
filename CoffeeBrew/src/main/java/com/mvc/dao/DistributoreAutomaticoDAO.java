package com.mvc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.helper.DBHelper;
import com.mvc.bean.DistributoreAutomaticoBean;

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
	
	public String getStato(int idDistributore) {
		
	}
}
