class Prodotto {
	nome;
	costo;
	quantita;
	nodeSelezionaProdotto;
	nodeRicaricaProdotto;
	
	constructor(nome, costo, quantita) {
		this.nome = nome;
		this.costo = costo;
		this.quantita = quantita;
		let prodottoReference = this;
		this.nodeSelezionaProdotto = templateSelezionaProdotto.cloneNode(true);
		this.nodeSelezionaProdotto.querySelector("#nomeProdotto").innerText = this.nome;
		this.nodeSelezionaProdotto.querySelector("#nomeProdotto").id = "";
		this.nodeSelezionaProdotto.querySelector("#costoProdotto").innerText = parseFloat(this.costo / 100).toFixed(2);
		this.nodeSelezionaProdotto.querySelector("#costoProdotto").id = "";
		this.nodeSelezionaProdotto.querySelector("#buttonProdotto").value = this.costo;
		this.nodeSelezionaProdotto.querySelector("#buttonProdotto").addEventListener("click", function() { setCost(prodottoReference) }, false);
		this.nodeSelezionaProdotto.querySelector("#productImg").src = "images/" + nome + ".png";
		
		/**
		this.nodeSelezionaProdotto.id = this.nome + "SelezionaProdotto";
		this.nodeSelezionaProdotto.children[0].id = "";
		this.nodeSelezionaProdotto.children[0].innerHTML = this.nome;
		
		this.nodeSelezionaProdotto.children[1].id = this.nome + "CostoProdotto";
		this.nodeSelezionaProdotto.children[1].innerHTML = parseFloat(this.costo / 100).toFixed(2);
		
		this.nodeSelezionaProdotto.children[2].id = this.nome + "ButtonProdotto"
		this.nodeSelezionaProdotto.children[2].name = "prodotto";
		this.nodeSelezionaProdotto.children[2].value = this.costo;
		this.nodeSelezionaProdotto.children[2].addEventListener("click", function() { setCost(prodottoReference) }, false);
		**/
		
		this.nodeRicaricaProdotto = templateRicaricaProdotto.cloneNode(true);
		this.nodeRicaricaProdotto.id = this.node + "RicaricaProdotto";
		this.nodeRicaricaProdotto.children[0].id = "";
		this.nodeRicaricaProdotto.children[0].innerHTML = this.nome;
		this.nodeRicaricaProdotto.children[1].id = this.nome + "QtyProdotto";
		this.nodeRicaricaProdotto.children[1].innerHTML = this.quantita;
		this.nodeRicaricaProdotto.children[2].id = this.nome + "ButtonRicaricaProdotto"
		this.nodeRicaricaProdotto.children[2].name = "ricarica";
		this.nodeRicaricaProdotto.children[2].addEventListener("click", function() { ricaricaProdotto(prodottoReference) }, false);
	}
	
	ricarica() {
		this.quantita = 100;
		this.nodeRicaricaProdotto.children[1].innerHTML = this.quantita;
	}
	
	consuma() {
		this.quantita -= 5;
	}
} 

divPulsantiProdotti = document.getElementById("pulsantiProdotti");
templateSelezionaProdotto = document.getElementById("templateSelezionaProdotto");

divListaProdotti = document.getElementById("listaProdotti");
templateRicaricaProdotto = document.getElementById("templateRicaricaProdotto");

let cappuccino = new Prodotto("cappuccino", 150, 80);
let espresso = new Prodotto("espresso", 100, 90);

let arrayProdotti = [];
arrayProdotti.push(cappuccino);
arrayProdotti.push(espresso);

arrayProdotti.forEach(prodotto => {
	console.log(prodotto);
	divPulsantiProdotti.appendChild(prodotto.nodeSelezionaProdotto);
	divListaProdotti.appendChild(prodotto.nodeRicaricaProdotto);
})

pulsantiProdotto = document.getElementsByName("prodotto");

for (let i = 0; i < pulsantiProdotto.length; i++) {
 	pulsantiProdotto[i].addEventListener("click", setCost, false);
	console.log("aggiunto");
}

let datiOccupante;

function isConnected() {
	if(!found) {
		const xhttp = new XMLHttpRequest();
		xhttp.onload = function() {
			occupante = JSON.parse(this.responseText);
			
			if(occupante.isTecnico != null) {
				if(occupante.isTecnico) {
					if(occupante.nome != null && occupante.idTecnico!= null) {
						found = true;
						
						divInterfacciaTecnico.hidden = false;
						nomeTecnicoLabel.innerHTML = occupante.nome;
						
						console.log(occupante);
					} else {
						console.log("errore dati tecnico")
					}
				} else {
					if(occupante.nome != null && occupante.centesimiCredito != null && occupante.idUtente != null) {
						found = true;
						datiOccupante = occupante;
						divInterfacciaUtente.hidden = false;
						nomeUtenteLabel.innerHTML = occupante.nome;
						creditoLabel.innerHTML = occupante.centesimiCredito / 100;
						inputIdUtente.value = occupante.idUtente;
						console.log(occupante);
					} else {
						console.log("errore dati utente")
					}
				}
			} else {
				console.log("nessun utente collegato");
			}
		}
		xhttp.open("GET", "DistributoreAutomaticoController?idDistributore=" + idTextbox.value, true);
		xhttp.send();
	}
}

function sendPurchase(prodotto) {
	if(prodottoScelto != null) {
		if(datiOccupante.credito >= prodottoScelto.costo) {
			const xhttp = new XMLHttpRequest();
			xhttp.onload = function() {
				console.log("inviata POST");
				found = false;
				console.log("consumo " + prodottoScelto);
				prodottoScelto.consuma();
			}
			xhttp.open("POST", "DistributoreAutomaticoController", true);
			xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhttp.send("importo=" + inputImporto.value + "&idDistributore=" + idTextbox.value + "&idUtente=" + inputIdUtente.value);
			clearInterfacciaUtente();
		} else {
			alert("Credito insufficente");
		}
	}
}

function clearInterfacciaUtente() {
	divInterfacciaUtente.hidden = true;
	inputImporto.value = "";
	labelScelta.innerHTML = "Seleziona un prodotto";
}

function exit() {
	divInterfacciaUtente.hidden = true;
	divInterfacciaTecnico.hidden = true;
	const xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		console.log("inviata POST");
		found = false;
	}
	xhttp.open("POST", "DistributoreAutomaticoController", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.send("exit=true&idDistributore=" + idTextbox.value);
}

function ricaricaProdotto(prodotto) {
	prodotto.ricarica();
}

let found = false;

document.getElementById("poll").addEventListener("click", startPolling, false);
idTextbox = document.getElementById("idDistributore");
divInterfacciaUtente = document.getElementById("interfacciaUtente");
divInterfacciaTecnico = document.getElementById("interfacciaTecnico");
acquistaButton = document.getElementById("acquistaButton");
acquistaButton.addEventListener("click", sendPurchase, false);
nomeUtenteLabel = document.getElementById("nomeUtente");
nomeTecnicoLabel = document.getElementById("nomeTecnico");
creditoLabel = document.getElementById("credito");

sceltaLabel = document.getElementById("scelta");
inputImporto = document.getElementById("importo");
inputIdUtente = document.getElementById("utente");

labelScelta = document.getElementById("scelta");

clearInterfacciaUtente();

function startPolling() {
	console.log("starting polling...")
	const interval = setInterval(isConnected, 2000);
}

let prodottoScelto;

function setCost(prodotto) {
	prodottoScelto = prodotto;
	inputImporto.value = this.value;
	labelScelta.innerHTML = "Importo da pagare: &euro;" + parseFloat(this.value / 100).toFixed(2);
	console.log("premuto " + this);
}
