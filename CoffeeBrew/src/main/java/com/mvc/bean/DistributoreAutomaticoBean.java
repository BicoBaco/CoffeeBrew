package com.mvc.bean;

public class DistributoreAutomaticoBean {
	private int idDistributore;
	private String locazione;
	private int occupanteUtente;
	private int occupanteTecnico;
	
	public int getIdDistributore() {
		return idDistributore;
	}
	public void setIdDistributore(int idDistributore) {
		this.idDistributore = idDistributore;
	}
	public String getLocazione() {
		return locazione;
	}
	public void setLocazione(String locazione) {
		this.locazione = locazione;
	}
	public int getOccupanteUtente() {
		return occupanteUtente;
	}
	public void setOccupanteUtente(int occupanteUtente) {
		this.occupanteUtente = occupanteUtente;
	}
	public int getOccupanteTecnico() {
		return occupanteTecnico;
	}
	public void setOccupanteTecnico(int occupanteTecnico) {
		this.occupanteTecnico = occupanteTecnico;
	}

}
