import { apiRoute } from "../../js/index.js";
import { logged_user } from "../../js/index.js";

const purchasesContainer = document.getElementsByClassName('purchases__container')[0]

async function loadPurchases() {
    let compras;
    if (logged_user.isAdmin) {
        compras = await fetch(apiRoute + "pedidos").then(response => response.json());
    } else {
        compras = await fetch(apiRoute + "pedido/usuario/" + logged_user.id).then(response => response.json());
    }
    console.log("compras = " + compras);
    compras.forEach(compra => {
        const itemCompra = document.createElement('div');
        itemCompra.classList.add('purchase__item__container');

        const orderIdLabel = document.createElement('h2');
        orderIdLabel.innerText = "Pedido #" + compra.id;
        orderIdLabel.classList.add('purchase__id__label');
        itemCompra.appendChild(orderIdLabel);

        if(logged_user.isAdmin) {
            const orderUserLabel = document.createElement('p');
            orderUserLabel.innerText = "Cliente: " + compra.user.nombre + " " + compra.user.apellidos;
            orderUserLabel.classList.add('purchase__user__label');
            itemCompra.appendChild(orderUserLabel);
        }

        const orderDateLabel = document.createElement('p');
        let fecha = new Date(compra.created_at);
        const minutos = fecha.getMinutes().toString().padStart(2, "0");
        const hora = fecha.getHours().toString().padStart(2, "0");
        const dia = fecha.getDate().toString().padStart(2, "0");
        const mes = (fecha.getMonth() + 1).toString().padStart(2, "0");
        const year = fecha.getFullYear();

        const fechaFormateada = dia + "/" + mes + "/" + year + " " + hora + ":" + minutos;

        orderDateLabel.innerText = "Fecha: " + fechaFormateada;
        orderDateLabel.classList.add('purchase__date__label');
        itemCompra.appendChild(orderDateLabel);

        const tabla = document.createElement('table');
        const thead = document.createElement('thead');
        const theadRow = document.createElement('tr');
        const theadRowProducto = document.createElement('th');
        theadRowProducto.innerText = "Producto";
        const theadRowCantidad = document.createElement('th');
        theadRowCantidad.innerText = "Cantidad";
        const theadRowPrecio = document.createElement('th');
        theadRowPrecio.innerText = "Precio";
        const theadRowSubtotal = document.createElement('th');
        theadRowSubtotal.innerText = "Subtotal";
        theadRow.appendChild(theadRowProducto);
        theadRow.appendChild(theadRowCantidad);
        theadRow.appendChild(theadRowPrecio);
        theadRow.appendChild(theadRowSubtotal);
        thead.appendChild(theadRow);
        tabla.appendChild(thead);

        const tbody = document.createElement('tbody');
        tbody.classList.add('purchase__detail__container');
        let total = 0;
        compra.details.forEach(detalle => {
            const tbodyRow = document.createElement('tr');
            const tbodyRowProducto = document.createElement('td');
            tbodyRowProducto.innerText = detalle.product.nombre;
            const tbodyRowCantidad = document.createElement('td');
            tbodyRowCantidad.innerText = detalle.cantidad;
            const tbodyRowPrecio = document.createElement('td');
            tbodyRowPrecio.innerText = detalle.precio + " Bs";
            const tbodyRowSubtotal = document.createElement('td');
            tbodyRowSubtotal.innerText = detalle.precio * detalle.cantidad + " Bs";
            tbodyRow.appendChild(tbodyRowProducto);
            tbodyRow.appendChild(tbodyRowCantidad);
            tbodyRow.appendChild(tbodyRowPrecio);
            tbodyRow.appendChild(tbodyRowSubtotal);
            tbody.appendChild(tbodyRow);
            total += detalle.precio * detalle.cantidad;
        });
        tabla.appendChild(tbody);
        
        const tfoot = document.createElement('tfoot');
        const tfootRow = document.createElement('tr');
        const tfootRowTotal = document.createElement('th');
        tfootRowTotal.innerText = "Total";
        const tfootRowTotalPrecio = document.createElement('th');
        tfootRowTotalPrecio.innerText = total + "Bs";
        tfootRow.appendChild(tfootRowTotal);
        tfootRow.appendChild(document.createElement('th'));
        tfootRow.appendChild(document.createElement('th'));

        tfootRow.appendChild(tfootRowTotalPrecio);
        tfoot.appendChild(tfootRow);
        tabla.appendChild(tfoot);

        itemCompra.appendChild(tabla);
        purchasesContainer.appendChild(itemCompra);
    });
}

loadPurchases();