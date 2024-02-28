import { apiRoute } from "../../js/index.js";

const categoryContainer = document.getElementsByClassName("header__categories__list")[0];

const fetchCategorias = async() => {
    const response = await fetch(apiRoute + "categoria");
    return await response.json();
}

const fetchSubcategorias = async() => {
    const response = await fetch(apiRoute + "subcategoria");
    return await response.json();
}

const cargarCategorias = async() => {
    const categorias = await fetchCategorias();
    const subcategorias = await fetchSubcategorias();
    for (let categoria of categorias) {
        const categoryItem = document.createElement("li");
        categoryItem.classList.add("header__category__item");
        const labelCategory = document.createElement("p");
        labelCategory.textContent = categoria.nombre;
        categoryItem.appendChild(labelCategory);
        const subcategoryContainer = document.createElement("ul");
        subcategoryContainer.classList.add("header__subcategories__list");
        categoryItem.appendChild(subcategoryContainer);
        for (let subcategoria of subcategorias) {
            if (subcategoria.categoria_id == categoria.id) {
                const subcategoryItem = document.createElement("li");
                subcategoryItem.classList.add("header__subcategory__item");
                let productPath
                if (document.URL.includes("index.html")) {
                    productPath = "pages/products/products.html";
                } else {
                    productPath = "../products/products.html";
                }
                subcategoryItem.innerHTML = `<a href="${productPath}?${subcategoria.id}">${subcategoria.nombre}</a>`;
                subcategoryContainer.appendChild(subcategoryItem);
            }
        }
        categoryContainer.appendChild(categoryItem);
    }

}

cargarCategorias();


