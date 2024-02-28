import { apiRoute } from "../../js/index.js";


async function loadAsideCategories(){
    const categories = await fetch(apiRoute + "categoria").then(response => response.json());
    const subCategories = await fetch(apiRoute + "subcategoria").then(response => response.json());

    const ul = document.getElementById("categories__nav__list");

    for (let category of categories) {
        const categoryItem = document.createElement("li");
        categoryItem.classList.add("category__item");
        ul.appendChild(categoryItem);

        const categoryName = document.createElement("p");
        categoryName.textContent = category.nombre;
        categoryItem.appendChild(categoryName);

        const subCategoriesList = document.createElement("ul");
        subCategoriesList.classList.add("subcategories__list");
        categoryItem.appendChild(subCategoriesList);

        for (let subCategory of subCategories) {
            if (subCategory.categoria_id != category.id) {
                continue;
            }
            const subCategoryItem = document.createElement("li");
            subCategoryItem.classList.add("subcategory__item");
            subCategoryItem.innerHTML = `<a href="../products/products.html?${subCategory.id}">${subCategory.nombre}</a>`;
            subCategoriesList.appendChild(subCategoryItem);
        }
        
    }
}

loadAsideCategories();