
const apiRoute = "http://localhost:8000/api/";
export {apiRoute};

const logged_user = JSON.parse(sessionStorage.getItem("user"));
export {logged_user};

if (logged_user != null){
    const accountContainer = document.getElementById("account__menu__container");
    accountContainer.textContent = logged_user.nombre;

    const optionsWrapper = document.createElement("div");
    optionsWrapper.setAttribute("class", "options__wrapper");

    accountContainer.appendChild(optionsWrapper);

    const comprasLink = document.createElement("a");
    comprasLink.setAttribute("href", "../../pages/purchases/purchases.html");
    comprasLink.setAttribute("class", "account__menu__item");
    comprasLink.textContent = "Mis Compras";
    optionsWrapper.appendChild(comprasLink);

    const logoutOption = document.createElement("button");
    logoutOption.setAttribute("class", "account__menu__item");
    logoutOption.textContent = "Cerrar Sesión";
    logoutOption.addEventListener("click", () => {
        sessionStorage.removeItem("user");
        window.location.href = "../../index.html";
    });
    optionsWrapper.appendChild(logoutOption);
}

const itemsCarrito = localStorage.getItem("carrito");
const numItemsLabel = document.getElementById("cart__size__label");

if (itemsCarrito != null){
    let carrito = JSON.parse(itemsCarrito);
    let numItems = 0;
    for (let producto of carrito.productos){
        numItems += producto.cantidad;
    }
    if (numItems > 0){
        numItemsLabel.textContent = numItems;
        numItemsLabel.style.display = "inline-block";
    }
} 





