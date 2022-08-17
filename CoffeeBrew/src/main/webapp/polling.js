function isConnected() {
	const xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		document.getElementById("demo").append(this.responseText);
	}
	xhttp.open("GET", "DistributoreAutomaticoController", true);
	xhttp.send();
}

document.getElementById("poll").addEventListener("click", isConnected, false);