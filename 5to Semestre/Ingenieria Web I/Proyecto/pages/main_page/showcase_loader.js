import { apiRoute } from "../../js/index.js";
const showcaseContainer = document.getElementsByClassName('products_container')[0];

let productosDestacados = JSON.parse(localStorage.getItem("productosDestacados"));
const productsHtml = []

if (productosDestacados === null){
    productosDestacados = [];
    const indices = [10, 11, 14, 20, 33, 40]
    for (let i = 0; i < 6; i++) {
        await fetch(apiRoute + "producto/" + indices[i])
        .then(response => response.json())
        .then(producto => {
            productosDestacados.push(producto);
        });
    }
    localStorage.setItem("productosDestacados", JSON.stringify(productosDestacados));
} 

for (let producto of productosDestacados){
    const item = document.createElement('figure');
    item.classList.add('products_item');

    const link = document.createElement('a');
    link.href = "../detail/detail.html?" + producto.id;
    item.appendChild(link);

    const img = document.createElement('img');
    img.src = producto.img_url;
    img.alt = "Producto " + producto.id;
    link.appendChild(img);

    const price = document.createElement('p');
    price.textContent = producto.precio + " Bs";
    item.appendChild(price);

    productsHtml.push(item);
}

function showProductsAccordingToWidth() {
    const containerWidth = showcaseContainer.clientWidth;
    let minProductWidth = 120;
    if(window.innerWidth > 360) {
        minProductWidth = 170;
    }
    if(window.innerWidth > 768) {
        minProductWidth = 220;
    }
    const numProductsToShow = Math.floor(containerWidth / minProductWidth);

    showcaseContainer.innerHTML = '';

    const numProducts = Math.max(1, numProductsToShow);
    
    for (let i = 0; i < numProducts; i++) {
        showcaseContainer.appendChild(productsHtml[i]);
    }
}

document.getElementById('sc__btn__previus').addEventListener('click', () => {
    productsHtml.unshift(productsHtml.pop());
    showProductsAccordingToWidth();
});

document.getElementById('sc__btn__next').addEventListener('click', () => {
    productsHtml.push(productsHtml.shift());
    showProductsAccordingToWidth();
}); 

window.addEventListener('load', showProductsAccordingToWidth);
window.addEventListener('resize', showProductsAccordingToWidth);

