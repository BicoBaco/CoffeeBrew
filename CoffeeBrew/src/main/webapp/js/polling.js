
let divInterfacciaUtente = document.getElementById("interfacciaUtente");
let divPulsantiProdotti = document.getElementById("pulsantiProdotti");
let templateSelezionaProdotto = document.getElementById("templateSelezionaProdotto");

let divInterfacciaTecnico = document.getElementById("interfacciaTecnico");
let divListaProdotti = document.getElementById("listaProdotti");
let templateRicaricaProdotto = document.getElementById("templateRicaricaProdotto");

arrayProdotti.forEach(prodotto => {
	divPulsantiProdotti.appendChild(prodotto.nodeSelezionaProdotto);
	divListaProdotti.appendChild(prodotto.nodeRicaricaProdotto);
})

let pulsantiProdotto = document.getElementsByName("prodotto");

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
						divWait.hidden = true;
						nomeTecnicoLabel.innerHTML = occupante.nome;
					} else {
						divWait.hidden = false;
					}
				} else {
					if(occupante.nome != null && occupante.centesimiCredito != null && occupante.idUtente != null) {
						found = true;
						datiOccupante = occupante;
						divInterfacciaUtente.hidden = false;
						divWait.hidden = true;
						nomeUtenteLabel.innerHTML = occupante.nome;
						creditoLabel.innerHTML = occupante.centesimiCredito / 100;
						inputIdUtente.value = occupante.idUtente;
					} else {
						divWait.hidden = false;
					}
				}
			} else {
				divWait.hidden = false;
			}
		}
		xhttp.open("GET", "DistributoreAutomaticoController?idDistributore=" + idTextbox.value, true);
		xhttp.send();
	}
}

function sendPurchase(prodotto) {
	if(prodottoScelto != null) {
		if(datiOccupante.centesimiCredito >= prodottoScelto.costo) {
			const xhttp = new XMLHttpRequest();
			xhttp.onload = function() {
				found = false;
				prodottoScelto.consuma();
				divWait.hidden = false;
			}
			xhttp.open("POST", "DistributoreAutomaticoController", true);
			xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhttp.send("importo=" + inputImporto.value + "&idDistributore=" + idTextbox.value + "&idUtente=" + inputIdUtente.value + "&idProdotto=" + prodottoScelto.idProdotto);
			clearInterfacciaUtente();
		} else {
			alert("Credito insufficente");
		}
	}
}

function clearInterfacciaUtente() {
	divInterfacciaUtente.hidden = true;
	inputImporto.value = "";
	labelScelta.innerHTML = "Nessun prodotto selezionato";
}

function exit() {
	divInterfacciaUtente.hidden = true;
	divInterfacciaTecnico.hidden = true;
	const xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		found = false;
		divWait.hidden = false;
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

acquistaButton = document.getElementById("acquistaButton");
acquistaButton.addEventListener("click", sendPurchase, false);

nomeUtenteLabel = document.getElementById("nomeUtente");
nomeTecnicoLabel = document.getElementById("nomeTecnico");
creditoLabel = document.getElementById("credito");

labelScelta = document.getElementById("scelta");
inputImporto = document.getElementById("importo");
inputIdUtente = document.getElementById("utente");

divPolling = document.getElementById("divPolling");
divCodiceDistributore = document.getElementById("divCodiceDistributore");
spanCodice = document.getElementById("spanCodice");

clearInterfacciaUtente();

function startPolling() {
	divPolling.hidden = true;
	divCodiceDistributore.hidden = false;
	spanCodice.innerText = idTextbox.value;
	const interval = setInterval(isConnected, 2000);
}

let textWait = document.getElementById("textWait");
let divWait = document.getElementById("divWait");

const intervalWait = setInterval(changeWaitText, 500);

function changeWaitText() {
	if(textWait.innerText == ".") {
		textWait.innerText = "..";
	} else if(textWait.innerText == "..") {
		textWait.innerText = "...";
	} else if(textWait.innerText == "...") {
		textWait.innerText = ".";
	}
}

let prodottoScelto;

function setCost(prodotto) {
	prodottoScelto = prodotto;
	inputImporto.value = prodotto.costo;
	labelScelta.innerHTML = "Importo da pagare: &euro;" + parseFloat(prodotto.costo / 100).toFixed(2);
}
