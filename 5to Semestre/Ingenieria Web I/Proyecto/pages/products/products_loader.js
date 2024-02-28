import { addProductToCart } from './product_interaction.js'
import { apiRoute } from "../../js/index.js";
import { injectForm } from './admin_functions.js';
import { deteleProduct } from './admin_functions.js';


let idSubcategoria = window.location.search.split('?').pop();

const fetchSubcategoria = async(id) => {
    const response = await fetch(apiRoute + "subcategoria/" + id);
    return await response.json();
}

const fetchProductosBySubcategoria = async(id) => {
    const response = await fetch(apiRoute + "producto/" + "categoria/" + id);
    return await response.json();
}

const cargarProductos = async() => {
    const subcategoria = await fetchSubcategoria(idSubcategoria);
    const productos = await fetchProductosBySubcategoria(idSubcategoria);
    const productsContainer = document.getElementsByClassName("products__container")[0];

    const subcategoryTitle = document.getElementsByTagName("h1")[0];
    subcategoryTitle.textContent = subcategoria.nombre;

    const user = JSON.parse(sessionStorage.getItem("user"));

    if(user && user.isAdmin) {
        const btnAddProduct = document.createElement("button");
        btnAddProduct.setAttribute("class", "add__product__button");
        const btnAddProductIcon = document.createElement("i");
        btnAddProductIcon.setAttribute("class", "fa-solid fa-plus-circle");
        btnAddProduct.appendChild(btnAddProductIcon);
        subcategoryTitle.insertAdjacentElement("afterend", btnAddProduct);
        btnAddProduct.addEventListener("click", () => {
            injectForm(true, null);
        });
    }    

    for (let producto of productos) {
        const productItem = document.createElement("figure");
        productItem.setAttribute("class", "product__item__container");

        const imageContainer = document.createElement("a");
        imageContainer.setAttribute("href", "../../pages/detail/detail.html?" + producto.id);
        productItem.appendChild(imageContainer);
        const image = document.createElement("img");
        image.setAttribute("src", producto.img_url);
        image.setAttribute("alt", producto.nombre);
        image.setAttribute("class", "product__img");
        imageContainer.appendChild(image);

        if(user && user.isAdmin) {
            const btnDelete = document.createElement("button");
            btnDelete.setAttribute("class", "product__item__delete__button");
            const btnDeleteIcon = document.createElement("i"); 
            btnDeleteIcon.setAttribute("class", "fa-solid fa-trash");
            btnDelete.appendChild(btnDeleteIcon);
            productItem.appendChild(btnDelete);

            btnDelete.addEventListener("click", () => {
                deteleProduct(producto.id);
            });

            const btnEdit = document.createElement("button");
            btnEdit.setAttribute("class", "product__item__edit__button");
            const btntEditIcon = document.createElement("i");
            btntEditIcon.setAttribute("class", "fa-solid fa-pen-to-square"); 
            btnEdit.appendChild(btntEditIcon);
            productItem.appendChild(btnEdit);

            btnEdit.addEventListener("click", () => {   
                injectForm(false, producto);
            });
        }

        const interactionContainer = document.createElement("div");
        interactionContainer.setAttribute("class", "product__item__interaction__container");
        productItem.appendChild(interactionContainer);

        const infoWrapper = document.createElement("div");
        infoWrapper.setAttribute("class", "product__item__info__wrapper");
        interactionContainer.appendChild(infoWrapper);

        const productTitle = document.createElement("figcaption");
        productTitle.textContent = producto.nombre;
        infoWrapper.appendChild(productTitle);

        const productPrice = document.createElement("p");
        productPrice.textContent = producto.precio + "Bs";
        infoWrapper.appendChild(productPrice);

        const addToCartButton = document.createElement("button");
        addToCartButton.setAttribute("class", "add__cart__button");
        addToCartButton.addEventListener("click", () => {
            addProductToCart(producto, 1)
            const lblSizeCarrito = document.getElementById("cart__size__label");
            lblSizeCarrito.textContent = +lblSizeCarrito.textContent + 1;
            if (lblSizeCarrito.style.display != "block") {
                lblSizeCarrito.style.display = "block";
            }
        })
        const buttonIcon = document.createElement("i");
        buttonIcon.setAttribute("class", "fa-solid fa-cart-shopping");
        addToCartButton.appendChild(buttonIcon);
        interactionContainer.appendChild(addToCartButton);

        productsContainer.appendChild(productItem);
    }
    
}

cargarProductos();

