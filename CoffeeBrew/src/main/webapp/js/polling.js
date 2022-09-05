
let divInterfacciaUtente = document.getElementById("interfacciaUtente");
let divPulsantiProdotti = document.getElementById("pulsantiProdotti");
let templateSelezionaProdotto = document.getElementById("templateSelezionaProdotto");

let divInterfacciaTecnico = document.getElementById("interfacciaTecnico");
let divListaProdotti = document.getElementById("listaProdotti");
let templateRicaricaProdotto = document.getElementById("templateRicaricaProdotto");

/*
let cappuccino = new Prodotto(10, "cappuccino", 150, 80);
let espresso = new Prodotto(11, "espresso", 100, 90);
let macchiato = new Prodotto(12, "macchiato", 120, 70);

arrayProdotti.push(espresso);
arrayProdotti.push(macchiato);
arrayProdotti.push(cappuccino);
*/

arrayProdotti.forEach(prodotto => {
	divPulsantiProdotti.appendChild(prodotto.nodeSelezionaProdotto);
	divListaProdotti.appendChild(prodotto.nodeRicaricaProdotto);
})

let pulsantiProdotto = document.getElementsByName("prodotto");

/*
for (let i = 0; i < pulsantiProdotto.length; i++) {
 	pulsantiProdotto[i].addEventListener("click", setCost, false);
	console.log("aggiunto");
}
*/

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
						
						console.log(occupante);
					} else {
						console.log("errore dati tecnico")
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
						console.log(occupante);
					} else {
						console.log("errore dati utente")
						divWait.hidden = false;
					}
				}
			} else {
				console.log("nessun utente collegato");
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
				console.log("inviata POST");
				found = false;
				console.log("consumo " + prodottoScelto);
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
		console.log("inviata POST");
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

clearInterfacciaUtente();

function startPolling() {
	console.log("starting polling...")
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
	console.log("premuto " + this);
}
