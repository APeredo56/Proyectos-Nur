import { logged_user } from "../../js/index.js";
import { apiRoute } from "../../js/index.js";


const btnRegistarCompra = document.getElementById('cart__buy__button');

async function realizarCompra(){

    if (logged_user === null){
        window.location.href = "../login/login.html";
        return;
    }

    const carritoCompra = JSON.parse(localStorage.getItem('carrito'));
    if (carritoCompra === null || carritoCompra.productos.length === 0){
        const lblCarritoVacio = document.getElementById('cart__empty__label');
        lblCarritoVacio.style.color = "red";
        lblCarritoVacio.style.fontSize = "1.8em";
        return;
    }
    let body = { "persona_id": logged_user.id}

    const pedido = await fetch(apiRoute + "pedido", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    }).then(response => response.json());

    for (let producto of carritoCompra.productos){
        let body = { "cantidad" : producto.cantidad, "precio" : producto.precio, "producto_id": producto.id, "pedido_id": pedido.id}
        await fetch(apiRoute + "detalle", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        }).then(response => response.json());
    }    

    localStorage.removeItem('carrito');
    window.location.href = "../../index.html";

}

btnRegistarCompra.addEventListener("click", realizarCompra);