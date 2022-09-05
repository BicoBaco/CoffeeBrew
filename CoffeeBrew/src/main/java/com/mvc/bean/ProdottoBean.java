package com.mvc.bean;

public class ProdottoBean {
	private int idProdotto;
	private String nome;
	private int costo;
	private int qtaVendute;
	
	public int getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	public int getQtaVendute() {
		return qtaVendute;
	}
	public void setQtaVendute(int qtaVendute) {
		this.qtaVendute = qtaVendute;
	}
}
