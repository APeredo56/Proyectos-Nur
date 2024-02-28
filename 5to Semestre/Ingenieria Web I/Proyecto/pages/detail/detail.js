import { addProductToCart } from '../products/product_interaction.js';
import { apiRoute } from "../../js/index.js";

let idProducto = window.location.search.split('?').pop();
let producto;

const btnPlus = document.getElementById("product__amount__selector__plus");
const btnMinus = document.getElementById("product__amount__selector__minus");
const btnAddToCart = document.getElementById("btn__add__cart__detail");
const btnComprar = document.getElementById("btn__buy__detail");
const amountInput = document.getElementById("product__amount__input");


const fetchProductoDetail = async (id) => {
    const response = await fetch(apiRoute + "producto/" + id);
    return await response.json();
}

const cargarProducto = async () => {
    producto = await fetchProductoDetail(idProducto);
    const productInfoWrapper = document.getElementsByClassName("product__detail__info__wrapper")[0];

    let detailElements = productInfoWrapper.children;
    detailElements[0].textContent = producto.nombre;
    detailElements[1].textContent += " " + producto.precio + " Bs";
    detailElements[2].textContent += producto.disponible? " Disponible" : " Agotado";

    const descriptionContainer = document.getElementsByClassName("product__description__container")[0];
    let description = document.createElement("p");
    description.textContent = producto.descripcion;
    descriptionContainer.appendChild(description);

    const imageContainer = document.getElementsByClassName("product__detail__img__container")[0];
    let image = document.createElement("img");
    image.setAttribute("alt", producto.nombre);
    image.src = producto.img_url;
    imageContainer.appendChild(image);
}

btnPlus.addEventListener("click", () => {
    amountInput.value++;
    });

btnMinus.addEventListener("click", () => {
    if (amountInput.value > 1) {
        amountInput.value--;
    }
});

btnAddToCart.addEventListener("click", () => {
    addProductToCart(producto, amountInput.value);
    const lblSizeCarrito = document.getElementById("cart__size__label");
    lblSizeCarrito.textContent = +lblSizeCarrito.textContent + +amountInput.value;
});

btnComprar.addEventListener("click", () => {
    addProductToCart(producto, amountInput.value);
    window.location.href = "../cart/cart.html";
});

cargarProducto();