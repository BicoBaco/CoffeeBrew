function isConnected() {
	const xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		utente = JSON.parse(this.responseText);
		if(utente.nome != null && utente.centesimiCredito != null) {
			divInterfaccia.hidden = false;
			console.log(utente);
		} else {
			console.log("nessun utente collegato");
		}
	}
	xhttp.open("GET", "DistributoreAutomaticoController?idDistributore=" + idTextbox.value, true);
	xhttp.send();
}

function sendPurchase() {
	divInterfaccia.hidden = true;
	const xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		console.log("inviata POST");
	}
	xhttp.open("POST", "DistributoreAutomaticoController", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.send("cost=20&idDistributore=" + idTextbox.value);
}

document.getElementById("poll").addEventListener("click", isConnected, false);
idTextbox = document.getElementById("idDistributore");
divInterfaccia = document.getElementById("interfaccia");
acquistaButton = document.getElementById("acquistaButton");
acquistaButton.addEventListener("click", sendPurchase, false);