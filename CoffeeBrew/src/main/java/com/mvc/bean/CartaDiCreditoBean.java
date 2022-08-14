package com.mvc.bean;

import java.sql.Date;

public class CartaDiCreditoBean {
	private int idCarta;
	private String numeroCarta;
	private String nomeSullaCarta;
	private Date dataScadenza;
	private int idUtente;
	
	public int getIdCarta() {
		return idCarta;
	}
	public void setIdCarta(int idCarta) {
		this.idCarta = idCarta;
	}
	public String getNumeroCarta() {
		return numeroCarta;
	}
	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}
	public String getNomeSullaCarta() {
		return nomeSullaCarta;
	}
	public void setNomeSullaCarta(String nomeSullaCarta) {
		this.nomeSullaCarta = nomeSullaCarta;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public int getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	
	
}
