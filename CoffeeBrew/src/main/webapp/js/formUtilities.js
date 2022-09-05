function strengthPass() {
	//aggiungere la class newPassword al tag input
	
	//const weakPass = new RegExp("^((?=.*[a-z]).{8,}|(?=.*[a-z])(?=.*[A-Z]).{8,})$");
	const mediumPass = new RegExp("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9]))).{6,}");
	const strongPass = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*]).{8,}$");

    password.classList.remove("forte", "media", "debole");

   	if (strongPass.test(password.value)) {
        password.classList.add("forte");
    } else if (mediumPass.test(password.value)) {
        password.classList.add("media");
    } else { 
		password.classList.add("debole"); }
}

function toggleVisualizza() {
    if (password.type === "password") {  
        password.type = "text";
        visualizzaBtn.value = "Nascondi";
    } else {  
        password.type = "password";
        visualizzaBtn.value = "Visualizza";  
    }  
}

password = document.getElementById('password');
visualizzaBtn = document.getElementById("visualizzaBtn");

if(password.classList.contains("newPassword")) password.addEventListener('keyup', strengthPass);
visualizzaBtn.addEventListener('click', toggleVisualizza);