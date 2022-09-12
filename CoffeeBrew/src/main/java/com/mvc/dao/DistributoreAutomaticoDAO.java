package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.helper.DBHelper;
import com.mvc.bean.DistributoreAutomaticoBean;
import com.mvc.bean.TecnicoBean;
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
	
	public UtenteBean getOccupanteUtente(int idDistributore) {
		try {
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement("SELECT idUtente, nome, centesimiCredito FROM DistributoreAutomatico, Utente WHERE idUtente = occupanteUtente AND idDistributore = ?");
			pstmt.setInt(1, idDistributore);
			ResultSet rs = pstmt.executeQuery();
			UtenteBean occupante = new UtenteBean();
			
			if(rs.next()) {
				occupante.setIdUtente(rs.getInt("idUtente"));
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
	
	public void impostaOccupatoUtente(int idDistributore, int idUtente) throws SQLException {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("UPDATE DistributoreAutomatico SET occupanteUtente = ? WHERE idDistributore = ?");
			pstmt.setInt(1, idUtente);
			pstmt.setInt(2, idDistributore);
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
		}
	}
	
	public void impostaLiberoUtente(int idDistributore) {
		try {
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement("UPDATE DistributoreAutomatico SET occupanteUtente = NULL WHERE idDistributore = ?");
			pstmt.setInt(1, idDistributore);
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public TecnicoBean getOccupanteTecnico(int idDistributore) {
		try {
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement("SELECT idTecnico, nome FROM DistributoreAutomatico, Tecnico WHERE idTecnico = occupanteTecnico AND idDistributore = ?");
			pstmt.setInt(1, idDistributore);
			ResultSet rs = pstmt.executeQuery();
			TecnicoBean occupante = new TecnicoBean();
			
			if(rs.next()) {
				occupante.setIdTecnico(rs.getInt("idTecnico"));
				occupante.setNome(rs.getString("nome"));
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
	
	public void impostaOccupatoTecnico(int idDistributore, int idTecnico) throws SQLException {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("UPDATE DistributoreAutomatico SET occupanteTecnico = ? WHERE idDistributore = ?");
			pstmt.setInt(1, idTecnico);
			pstmt.setInt(2, idDistributore);
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
		}
	}

	public boolean isOccupato(int idDistributore) throws SQLException {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT occupanteTecnico, occupanteUtente FROM DistributoreAutomatico WHERE idDistributore = ?");
			pstmt.setInt(1, idDistributore);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt("occupanteTecnico") != 0) {
					return true;
				}
				if(rs.getInt("occupanteUtente") != 0) {
					return true;
				}
			} else {
				throw new SQLException("Distributore non esistente");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			pstmt.close();
		}
		
		return false;
	}
	
	public void liberaDistributore(int idDistributore) throws SQLException {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("UPDATE DistributoreAutomatico SET occupanteUtente = NULL, occupanteTecnico = NULL WHERE idDistributore = ?");
			pstmt.setInt(1, idDistributore);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
		}
	}
	
	public static boolean registraDistributoreAutomatico(DistributoreAutomaticoBean registrazioneDistributoreAutomatico) throws SQLException {
		String locazione = registrazioneDistributoreAutomatico.getLocazione();
		Connection connection = null; 
		PreparedStatement pstmt = null;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("INSERT INTO DistributoreAutomatico (locazione) VALUES (?)");
			pstmt.setString(1, locazione);
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
	
	public static ArrayList<DistributoreAutomaticoBean> getDistributori() throws SQLException {
		ArrayList<DistributoreAutomaticoBean> listaDistributori = new ArrayList<DistributoreAutomaticoBean>();
		
		Connection connection = null;	
		PreparedStatement pstmt = null;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("SELECT * FROM DistributoreAutomatico");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DistributoreAutomaticoBean distr = new DistributoreAutomaticoBean();
				distr.setIdDistributore(rs.getInt("idDistributore"));
				distr.setLocazione(rs.getString("locazione"));
				distr.setOccupanteUtente(rs.getInt("occupanteUtente"));
				distr.setOccupanteTecnico(rs.getInt("occupanteTecnico"));
				listaDistributori.add(distr);
			}

			return listaDistributori;			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connection.close();
		}
		
		return null;
	}
	
	public static boolean rimuoviDistributoreAutomatico(int idDistributore) throws SQLException {
		Connection connection = null;	
		PreparedStatement pstmt = null;
		int result;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("DELETE FROM DistributoreAutomatico WHERE idDistributore = ?");
			pstmt.setInt(1, idDistributore);
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