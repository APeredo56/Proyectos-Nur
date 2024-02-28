const registerForm = document.getElementById('register__form');
const inName = document.getElementById('input__name');
const inEmail = document.getElementById('input__email');
const inPassword = document.getElementById('input__password');
const inPasswordConfirm = document.getElementById('input__password__confirmation');



function esEmailValido(email){
	var pattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return pattern.test(email); 
};

function validateForm(){
	let isValid = true;
	const errorSpans = document.getElementsByClassName('error__span');
	for(let error of errorSpans){
		error.style.display = 'none';
	}
	if(inName.value == ""){
		const errorName = document.getElementById('error__name');
		errorName.textContent = "Este Campo es Obligatorio";
		errorName.style.display = 'block';
		inName.insertAdjacentElement("afterend", errorName);
		isValid = false;
	}
	if(inEmail.value == ""){
		const errorEmail = document.getElementById('error__email');
		errorEmail.textContent = "Este Campo es Obligatorio";
		inEmail.insertAdjacentElement("afterend", errorEmail);
		errorEmail.style.display = 'block';
		isValid = false;
	} else if(!esEmailValido(inEmail.value)){
		const errorEmail = document.getElementById('error__email');
		errorEmail.textContent = "El correo electrónico no tiene un formato válido";
		errorEmail.style.display = 'block';
		inEmail.insertAdjacentElement("afterend", errorEmail);
		isValid = false;
	}

	if(inPassword.value == ""){
		const errorPassword = document.getElementById('error__password');
		errorPassword.textContent = "Este Campo es Obligatorio";
		inPassword.insertAdjacentElement("afterend", errorPassword);
		errorPassword.style.display = 'block';
		isValid = false;
	} else if(inPassword.value.length < 6){
		const errorPassword = document.getElementById('error__password');
		errorPassword.textContent = "La contraseña debe tener al menos 6 caracteres";
		inPassword.insertAdjacentElement("afterend", errorPassword);
		errorPassword.style.display = 'block';
		isValid = false;
	}

	if(inPasswordConfirm.value == ""){
		const errorPasswordConfirm = document.getElementById('error__password__confirm');
		errorPasswordConfirm.textContent = "Este Campo es Obligatorio";
		inPasswordConfirm.insertAdjacentElement("afterend", errorPasswordConfirm);
		errorPasswordConfirm.style.display = 'block';
		isValid = false;
	} else if(inPassword.value != inPasswordConfirm.value){
		const errorPasswordConfirm = document.getElementById('error__password__confirm');
		errorPasswordConfirm.textContent = "Las contraseñas no coinciden";
		inPasswordConfirm.insertAdjacentElement("afterend", errorPasswordConfirm);
		errorPasswordConfirm.style.display = 'block';
		isValid = false;
	}

	if(!isValid){
		return false;
	}

	return true;	
}

registerForm.addEventListener('submit', (event) =>{
	if(!validateForm()){
		event.preventDefault();
	}
});

