import { apiRoute } from "../../js/index.js";

async function injectForm(isCreating, product){
    const container = document.createElement('div');
    container.classList.add('product__administration__form__container');
    
    const form = document.createElement('form');
    form.setAttribute('id', 'product__administration__form');
    container.appendChild(form);

    const closeBtn = document.createElement('button');
    closeBtn.setAttribute('id', 'adm__form__close__button');
    closeBtn.setAttribute('type', 'button');
    closeBtn.addEventListener('click', () => {
        container.remove();
    });
    const closeBtnIcon = document.createElement('i');
    closeBtnIcon.setAttribute('class', 'fa-solid fa-xmark');
    closeBtn.appendChild(closeBtnIcon);
    form.appendChild(closeBtn);

    const formTitle = document.createElement('h2');
    formTitle.setAttribute('id', 'form__action__label');
    if(!isCreating){
        formTitle.textContent = 'Editar Producto';
    } else {
        formTitle.textContent = 'Agregar Producto';        
    }
    form.appendChild(formTitle);

    const formInputContainer = document.createElement('div');
    formInputContainer.setAttribute('class', 'form__input__container');
    form.appendChild(formInputContainer);

    const formLabelName = document.createElement('label');
    formLabelName.setAttribute('for', 'adm__product__name');
    formLabelName.textContent = 'Producto';
    const formInputName = document.createElement('input');
    formInputName.setAttribute('id', 'adm__product__name');
    formInputName.setAttribute('type', 'text');
    formInputContainer.appendChild(formLabelName);
    formInputContainer.appendChild(formInputName);

    const formLabelPrice = document.createElement('label');
    formLabelPrice.setAttribute('for', 'adm__product__price');
    formLabelPrice.textContent = 'Precio';
    const formInputPrice = document.createElement('input');
    formInputPrice.setAttribute('id', 'adm__product__price');
    formInputPrice.setAttribute('type', 'number');
    formInputContainer.appendChild(formLabelPrice);
    formInputContainer.appendChild(formInputPrice);

    const formLabelDescription = document.createElement('label');
    formLabelDescription.setAttribute('for', 'adm__txt__description');
    formLabelDescription.textContent = 'Descripción';
    const formInputDescription = document.createElement('textarea');
    formInputDescription.setAttribute('id', 'adm__txt__description');
    formInputDescription.setAttribute('rows', '5');
    formInputDescription.setAttribute('cols', '30');
    formInputContainer.appendChild(formLabelDescription);
    formInputContainer.appendChild(formInputDescription);

    const formLabelCategory = document.createElement('label');
    formLabelCategory.setAttribute('for', 'adm__product__category');
    formLabelCategory.textContent = 'Categoría';
    const formSelectCategory = document.createElement('select');
    formSelectCategory.setAttribute('id', 'adm__product__category');
    formInputContainer.appendChild(formLabelCategory);
    formInputContainer.appendChild(formSelectCategory);

    await fetch(apiRoute + 'subcategoria').then(response => response.json()).then(data => {
        for(let subcat of data){
            const option = document.createElement('option');
            option.setAttribute('value', subcat.id);
            option.textContent = subcat.nombre;
            option.value = subcat.id;
            formSelectCategory.appendChild(option);
        }
        if(!isCreating){
            formSelectCategory.value = product.id_categoria;
        } else{
            formSelectCategory.value = -1;
        }
    });

    const formImage = document.createElement('img');
    formImage.setAttribute('id', 'adm__product__image__preview');
    formInputContainer.appendChild(formImage);


    const formInputImage = document.createElement('input');
    formInputImage.setAttribute('id', 'adm__product__image');
    formInputImage.setAttribute('type', 'file');
    formInputImage.setAttribute('accept', 'image/png', 'image/jpeg', 'image/jpg');
    formInputContainer.appendChild(formInputImage);

    formInputImage.addEventListener('change', () => {
        const reader = new FileReader();
        reader.addEventListener('load', (event) => {
            formImage.setAttribute('src', reader.result);
        }
        );
        reader.readAsDataURL(formInputImage.files[0]);
        formImage.style.display = 'block';
    });

    const listaImagenes = [];
    imagen.addEventListener('change', () => {
        const reader = new FileReader();
        reader.addEventListener('load', (event) => {
            listaImagenes.push(reader.result);
        }
        );
        for(const img of imagen.files){
            reader.readAsDataURL(img);
        }
    });

    const btnCancelar = document.createElement('input');
    btnCancelar.setAttribute('id', 'adm__form__cancel__button');
    btnCancelar.setAttribute('value', 'Cancelar');
    btnCancelar.setAttribute('class', 'adm__form__button');
    btnCancelar.setAttribute('type', 'button');
    btnCancelar.addEventListener('click', () => {
        container.remove();
    });

    const btnWrapper = document.createElement('div');
    btnWrapper.setAttribute('class', 'adm__form__button__wrapper');
    form.appendChild(btnWrapper);

    const btnAceptar = document.createElement('input');
    btnAceptar.setAttribute('id', 'adm__form__accept__button');
    btnAceptar.setAttribute('value', 'Aceptar');
    btnAceptar.setAttribute('class', 'adm__form__button');
    btnAceptar.setAttribute('type', 'submit');

    form.addEventListener('submit', async (event) => {
        event.preventDefault();
        const errors = document.getElementsByClassName('form__error__message');
        for (const error of [...errors]) {
            error.remove();
        }
        let hasErrors = false;
        if(formInputName.value.length < 1){
            const error = document.createElement('p');
            error.setAttribute('class', 'form__error__message');
            error.textContent = 'El nombre del producto no puede estar vacío';
            formInputName.insertAdjacentElement('beforebegin', error);
            hasErrors = true;
        }

        if(formInputPrice.value.length < 1){
            const error = document.createElement('p');
            error.setAttribute('class', 'form__error__message');
            error.textContent = 'El precio del producto no puede estar vacío';
            formInputPrice.insertAdjacentElement('beforebegin', error);
            hasErrors = true;
        }

        if(formInputDescription.value.length < 1){
            const error = document.createElement('p');
            error.setAttribute('class', 'form__error__message');
            error.textContent = 'La descripción del producto no puede estar vacía';
            formInputDescription.insertAdjacentElement('beforebegin', error);
            hasErrors = true;
        }

        if(formInputImage.files.length < 1 && isCreating){
            const error = document.createElement('p');
            error.setAttribute('class', 'form__error__message');
            error.textContent = 'Debe seleccionar una imagen';
            formInputImage.insertAdjacentElement('beforebegin', error);
            hasErrors = true;
        }

        if(hasErrors){
            return;
        }
        let body = {
            "nombre": formInputName.value,
            "precio": formInputPrice.value, 
            "disponible": 1, 
            "descripcion": formInputDescription.value, 
            "img_url": formImage.src,  
            "id_categoria": +formSelectCategory.value
        };
        if(isCreating){
            await fetch(apiRoute + 'producto', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(body)
            }).then(response => {
                if(response.ok){
                    container.remove();
                    location.reload();
                }
            })       
        } else{
            await fetch(apiRoute + 'producto/' + product.id, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(body)
            }).then(response => {
                if(response.ok){
                    container.remove();
                    location.reload();
                }
            })

        }
        
    });


    btnWrapper.appendChild(btnCancelar);
    btnWrapper.appendChild(btnAceptar);

    const main = document.getElementsByTagName('main')[0];
    main.appendChild(container);

    if(!isCreating){
        formInputName.value = product.nombre;
        formInputPrice.value = product.precio;
        formInputDescription.value = product.descripcion;
        formImage.setAttribute('src', product.img_url);
        formImage.style.display = 'block';
    }

}

export {injectForm};

async function deteleProduct(productID){
    const verification = await fetch(apiRoute + 'detalle/producto/' + productID
    ).then(response => response.json());

    if(verification.message === 'El producto tiene ventas asociadas.'){
        const errorMsg = "No se puede eliminar el producto porque tiene ventas asociadas";
        document.body.appendChild(createCustomDialog(errorMsg));
        return;
    } else {
        if(!confirm('¿Está seguro que desea eliminar el producto?')){
            return;
        }
    }
    
    const response = await fetch(apiRoute + 'producto/' + productID, {
        method: 'DELETE'
    });
    if(response.ok){
        location.reload();
    }
}

export {deteleProduct};

function createCustomDialog(errorMessage){
    const messageBox = document.createElement('div');
    messageBox.style.position = 'fixed';
    messageBox.style.top = '50%';
    messageBox.style.left = '50%';
    messageBox.style.width = '200px';
    messageBox.style.transform = 'translate(-50%, -50%)';
    messageBox.style.padding = '10px';
    messageBox.style.backgroundColor = '#48ff0e';
    messageBox.style.border = 'solid 1px #25670D';
    messageBox.style.textAlign = 'center';
    messageBox.style.zIndex = '1000';

    const message = document.createElement('p');
    message.textContent = errorMessage;
    messageBox.appendChild(message);

    const btnAceptar = document.createElement('input');
    btnAceptar.setAttribute('type', 'button');
    btnAceptar.setAttribute('value', 'Aceptar');
    btnAceptar.style.marginTop = '10px';
    btnAceptar.style.padding = '5px';
    btnAceptar.style.backgroundColor = '#25670D';
    btnAceptar.style.color = '#fff';
    btnAceptar.style.border = 'none';
    btnAceptar.addEventListener('click', () => {
        messageBox.remove();
    });
    messageBox.appendChild(btnAceptar);

    return messageBox;
}





