function isConnected() {
	const xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		if(this.responseText === "occupato") {
			divInterfaccia.hidden = false;
		}
	}
	xhttp.open("GET", "DistributoreAutomaticoController?idDistributore=" + idTextbox.value, true);
	xhttp.send();
}

function sendPurchase() {
	divInterfaccia.hide = true;
	const xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		console.log("inviata POST");
	}
	xhttp.open("POST", "DistributoreAutomaticoController", true);
	xhttp.send("cost=20&idDistributore=" + idTextbox.value);
}

document.getElementById("poll").addEventListener("click", isConnected, false);
idTextbox = document.getElementById("idDistributore");
divInterfaccia = document.getElementById("interfaccia");
acquistaButton = document.getElementById("acquistaButton");
acquistaButton.addEventListener("click", sendPurchase, false);