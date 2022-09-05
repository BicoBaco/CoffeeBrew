class Prodotto {
	idProdotto;
	nome;
	costo;
	quantita;
	nodeSelezionaProdotto;
	nodeRicaricaProdotto;
	
	constructor(idProdotto, nome, costo, quantita) {
		this.idProdotto = idProdotto;
		this.nome = nome;
		this.costo = costo;
		this.quantita = quantita;
		let prodottoReference = this;
		this.nodeSelezionaProdotto = templateSelezionaProdotto.cloneNode(true);
		this.nodeSelezionaProdotto.hidden = false;
		this.nodeSelezionaProdotto.querySelector("#nomeProdotto").innerText = this.nome;
		this.nodeSelezionaProdotto.querySelector("#nomeProdotto").id = "";
		this.nodeSelezionaProdotto.querySelector("#costoProdotto").innerText = parseFloat(this.costo / 100).toFixed(2);
		this.nodeSelezionaProdotto.querySelector("#costoProdotto").id = "";
		this.nodeSelezionaProdotto.querySelector("#buttonProdotto").value = this.costo;
		this.nodeSelezionaProdotto.querySelector("#buttonProdotto").addEventListener("click", function() { setCost(prodottoReference) }, false);
		this.nodeSelezionaProdotto.querySelector("#productImg").src = "images/" + nome + ".png";
		
		this.nodeRicaricaProdotto = templateRicaricaProdotto.cloneNode(true);
		this.nodeRicaricaProdotto.hidden = false;
		this.nodeRicaricaProdotto.querySelector("#nomeRicaricaProdotto").innerText = this.nome;
		this.nodeRicaricaProdotto.querySelector("#nomeRicaricaProdotto").id = "";
		this.nodeRicaricaProdotto.querySelector("#qtyProdotto").innerText = this.quantita;
		this.nodeRicaricaProdotto.querySelector("#qtyProdotto").id = this.nome + "QtyProdotto";
		this.nodeRicaricaProdotto.querySelector("#pulsanteRicarica").addEventListener("click", function() { ricaricaProdotto(prodottoReference) }, false);
		this.nodeRicaricaProdotto.querySelector("#pulsanteRicarica").id = "";
		this.nodeRicaricaProdotto.querySelector("#productImgRicarica").src = "images/" + nome + ".png";
	}
	
	ricarica() {
		this.quantita = 100;
		this.nodeRicaricaProdotto.querySelector("#" + this.nome + "QtyProdotto").innerText = this.quantita;
	}
	
	consuma() {
		this.quantita -= 5;
		this.nodeRicaricaProdotto.querySelector("#" + this.nome + "QtyProdotto").innerText = this.quantita;
	}
} 