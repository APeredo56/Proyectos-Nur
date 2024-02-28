const carritoString = localStorage.getItem('carrito');
const itemsContainer = document.getElementsByClassName('cart__items__container')[0];
const lblTotal = document.getElementById('cart__total__price__label');
const lblCarrito = document.getElementById('cart__size__label');
let total = 0;

function cargarCarrito() {
    if (carritoString === null) {
        return;
    }
    carrito = JSON.parse(carritoString);

    const cartEmpty = document.createElement('h2');
    cartEmpty.textContent = "El carrito está vacío";
    cartEmpty.setAttribute('id', 'cart__empty__label');

    if (carrito.productos.length === 0) {
        itemsContainer.appendChild(cartEmpty);
    }

    carrito.productos.forEach(producto => {
        const item = document.createElement('div');
        item.classList.add('cart__item');

        const header = document.createElement('div');
        header.classList.add('cart__item__header__wrapper');

        const productName = document.createElement('h2');
        productName.textContent = producto.nombre;
        const btnDelete = document.createElement('button');
        const btnDeleteIcon = document.createElement('i');
        btnDeleteIcon.setAttribute('class', 'fa-solid fa-trash-can');
        btnDelete.appendChild(btnDeleteIcon);
        btnDelete.addEventListener('click', () => {
            modificarProductoCarrito(producto, 0);
            item.remove();
            lblCarrito.textContent = +lblCarrito.textContent - lblAmount.textContent;
            if (lblCarrito.textContent === '0') {
                itemsContainer.appendChild(cartEmpty);
                lblCarrito.display = 'none';   
            }
            
            let currentSubtotal = parseInt(lblSubtotal.textContent.split('Bs ')[1]);
            console.log(currentSubtotal)
            console.log(lblTotal.textContent)
            let currentTotal = parseInt(lblTotal.textContent.split('Bs ')[1]);
            lblTotal.textContent = "Bs " + (currentTotal - currentSubtotal)
        });

        header.appendChild(productName);
        header.appendChild(btnDelete);
        item.appendChild(header);

        const productImg = document.createElement('img');
        productImg.src = producto.img_url;
        productImg.alt = producto.nombre;
        item.appendChild(productImg);

        const amountWrapper = document.createElement('div');
        amountWrapper.classList.add('cart__item__amount__selector');
        const lblAmount = document.createElement('label');
        lblAmount.textContent = producto.cantidad;
        const btnLess = document.createElement('button');
        btnLess.id = "cart__item__minus";
        const btnLessIcon = document.createElement('i');
        btnLessIcon.setAttribute('class', 'fa-solid fa-minus');
        btnLess.appendChild(btnLessIcon);
        btnLess.addEventListener('click', () => {
            let cantidadActual = +lblAmount.textContent -1;
            if (cantidadActual > 0) {
                lblAmount.textContent = cantidadActual;
                modificarProductoCarrito(producto, cantidadActual);
                total -= producto.precio;
                lblTotal.textContent = "Bs " + (total);
                lblSubtotal.textContent = "Bs " + producto.precio * cantidadActual;
                lblCarrito.textContent = +lblCarrito.textContent - 1;
            }
        });

        const btnMore = document.createElement('button');
        btnMore.id = "cart__item__plus";
        const btnMoreIcon = document.createElement('i');
        btnMoreIcon.setAttribute('class', 'fa-solid fa-plus');
        btnMore.appendChild(btnMoreIcon);
        btnMore.addEventListener('click', () => {
            let cantidadActual = +lblAmount.textContent + 1;
            lblAmount.textContent = cantidadActual;
            modificarProductoCarrito(producto, cantidadActual);
            total += producto.precio;
            lblTotal.textContent = "Bs " + (total);
            lblSubtotal.textContent = "Bs " + producto.precio * cantidadActual;
            lblCarrito.textContent = +lblCarrito.textContent + 1;
        });

        amountWrapper.appendChild(btnMore);
        amountWrapper.appendChild(lblAmount);
        amountWrapper.appendChild(btnLess);
        item.appendChild(amountWrapper);

        const lblPrice = document.createElement('p');
        lblPrice.classList.add('cart__item__price__label');
        lblPrice.textContent = "Bs " + producto.precio;
        item.appendChild(lblPrice);

        const titleSubtotal = document.createElement('h3');
        titleSubtotal.textContent = "Subtotal";
        titleSubtotal.style.margin = "0";
        const lblSubtotal = document.createElement('p');
        lblSubtotal.textContent = "Bs " + producto.precio * producto.cantidad;

        item.appendChild(titleSubtotal);
        item.appendChild(lblSubtotal);

        itemsContainer.appendChild(item);

        total += producto.precio * producto.cantidad;
        });
    lblTotal.textContent = "Bs " + total;
}

function modificarProductoCarrito(producto, cantidad) {
    const carrito = JSON.parse(localStorage.getItem('carrito'));
    const index = carrito.productos.findIndex(p => p.id === producto.id);
    if (cantidad === 0) {
        carrito.productos.splice(index, 1);
    } else {
        carrito.productos[index].cantidad = cantidad;
    }
    localStorage.setItem('carrito', JSON.stringify(carrito));
}

cargarCarrito();