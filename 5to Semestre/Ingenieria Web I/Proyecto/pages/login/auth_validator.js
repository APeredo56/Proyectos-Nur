import { apiRoute } from "../../js/index.js";

const formLogin = document.getElementById('log__in__form');
const formRegister = document.getElementById('sign__up__form');

formLogin.addEventListener("submit", login);
formRegister.addEventListener("submit", register);

async function login(event) {
    event.preventDefault();
    removeErrors();

    const inEmail = document.getElementById('email__login__input');
    const inPassword = document.getElementById('password__login__input');
    const errorEmail = document.getElementById('login__email__error');
    const errorPassword = document.getElementById('login__password__error');
    let valid = true;
    if (inEmail.value == ""){
        errorEmail.textContent = "El correo no puede estar vacío";
        errorEmail.style.display = "block";
        valid = false;
    } else if(inEmail.value.split("@").length != 2){
        errorEmail.textContent = "El correo no es válido";
        errorEmail.style.display = "block";
        valid = false;
    }

    if (inPassword.value == ""){
        errorPassword.textContent = "La contraseña no puede estar vacía";
        errorPassword.style.display = "block";
        valid = false;
    } 

    if (!valid){
        return;
    }

    let body = { "correo": inEmail.value, "password": inPassword.value };

    fetch(apiRoute + "login", {
        method: 'POST',
        body: JSON.stringify(body),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        return response.json();
    }).then(data => {
        if (data.message == "Inicio de sesión exitoso"){
            sessionStorage.setItem("user", JSON.stringify(data.user));
            if(data.user.isAdmin){
                window.location.href = "../products/products.html?1";
            } else{
                window.location.href = "../../index.html";
            }
        } else {
            errorEmail.textContent = "El usuario no existe";
            errorEmail.style.display = "block";
        }
    })
}

async function register(event) {
    event.preventDefault();
    removeErrors();

    const inName = document.getElementById('name__input');
    const inLastName = document.getElementById('last__name__input');
    const inEmail = document.getElementById('email__sign__up__input');
    const inPassword = document.getElementById('password__sign__up__input');
    const inCellphone = document.getElementById('phone__input');
    const inAge = document.getElementById('age__input');

    let name = inName.value;
    let lastName = inLastName.value;
    let email = inEmail.value;
    let password = inPassword.value;
    let cellphone = inCellphone.value;
    let age = inAge.value;

    let valid = true;
    if (name == ""){
        const errorName = document.getElementById('sign__up__name__error');
        errorName.textContent = "El nombre no puede estar vacío";
        errorName.style.display = "block";
    }
    if (lastName == ""){
        const errorLastName = document.getElementById('sign__up__last__name__error');
        errorLastName.textContent = "Los apellidos no pueden estar vacíos";
        errorLastName.style.display = "block";
    }

    const errorEmail = document.getElementById('sign__up__email__error');
    if (email == ""){
        errorEmail.textContent = "El correo no puede estar vacío";
        errorEmail.style.display = "block";
    } else if(inEmail.value.split("@").length != 2){
        errorEmail.textContent = "El correo no es válido";
        errorEmail.style.display = "block";
        valid = false;
    }

    if (password == ""){
        const errorPassword = document.getElementById('sign__up__password__error');
        errorPassword.textContent = "La contraseña no puede estar vacía";
        errorPassword.style.display = "block";
    } else if (password.length < 8){
        const errorPassword = document.getElementById('sign__up__password__error');
        errorPassword.textContent = "La contraseña debe tener al menos 8 caracteres";
        errorPassword.style.display = "block";
    }

    if (cellphone == ""){
        const errorCellphone = document.getElementById('sign__up__phone__error');
        errorCellphone.textContent = "El teléfono no puede estar vacío";
        errorCellphone.style.display = "block";
    }

    if (age == ""){
        const errorAge = document.getElementById('sign__up__age__error');
        errorAge.textContent = "La edad no puede estar vacía";
        errorAge.style.display = "block";
    }

    if (!valid){
        return;
    }

    let body = { "nombre": name, "apellidos": lastName, "correo": email, "password": password, "telefono": cellphone, "edad": age, "isAdmin": 0};
    fetch(apiRoute + "register", {
        method: 'POST',
        body: JSON.stringify(body),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        return response.json();
    }).then(data => {
        if (data.message == "Usuario creado correctamente"){
            sessionStorage.setItem("user", JSON.stringify(data.user));
            if(data.user.isAdmin){
                window.location.href = "../products/products.html?1";
            } else{
                window.location.href = "../../index.html";
            }
        } else if(data.reason.correo[0] == "The correo has already been taken."){
            errorEmail.textContent = "El correo ya esta en uso";
            errorEmail.style.display = "block";
        } else {
            console.log(data);
        }
    });
    
}

function removeErrors() {
    const errors = document.getElementsByClassName('auth__error');
    for (let error of errors) {
        error.style.display = "none";
    }
}

