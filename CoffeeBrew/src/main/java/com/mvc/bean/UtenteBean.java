package com.mvc.bean;

public class UtenteBean {
	private int idUtente;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private int centesimiCredito;
	private CartaDiCreditoBean[] listaCarte;
	
	public int getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCentesimiCredito() {
		return centesimiCredito;
	}
	public void setCentesimiCredito(int centesimiCredito) {
		this.centesimiCredito = centesimiCredito;
	}
	public CartaDiCreditoBean[] getListaCarte() {
		return listaCarte;
	}
	public void setListaCarte(CartaDiCreditoBean[] listaCarte) {
		this.listaCarte = listaCarte;
	}
	
	
	
}
