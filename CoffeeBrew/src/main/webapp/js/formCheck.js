password = document.querySelector("[type='password']");
passButton = document.getElementById("passButton");

if(password.id === "newPassword") password.addEventListener('input', strengthVal);
passButton.addEventListener('click', toggleVisibility);

function strengthVal() {
	//const weakPass = new RegExp("^((?=.*[a-z]).{8,}|(?=.*[a-z])(?=.*[A-Z]).{8,})$");
	const mediumPass = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$");
	const strongPass = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*]).{8,}$");

    password.classList.remove("forte", "media", "debole");

    if (password.value == "") password.classList.remove("forte", "media", "debole");

    else if (strongPass.test(password.value))
        password.classList.add("forte");
    else if (mediumPass.test(password.value))
        password.classList.add("media");
    else password.classList.add("debole");
}

function toggleVisibility() {
    if (password.type === "password") {  
        password.type = "text";
        passButton.value = "Nascondi";
    } else {  
        password.type = "password";
        passButton.value = "Visualizza";  
    }  
}