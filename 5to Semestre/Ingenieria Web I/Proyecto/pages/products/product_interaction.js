function addProductToCart(producto, cantidad){
    const carritoString = localStorage.getItem("carrito");
    let carrito;

    if(carritoString){
        carrito = JSON.parse(carritoString);
    } else {
        carrito = {
            "productos": []
        }
    }
    const indexProducto = carrito.productos.findIndex(productoCarrito => productoCarrito.id === producto.id)
    if(indexProducto === -1){
        producto.cantidad = +cantidad;
        carrito.productos.push(producto);
    } else {
        carrito.productos[indexProducto].cantidad += +cantidad;
    }
    localStorage.setItem("carrito", JSON.stringify(carrito));
    console.log(carrito);
}

export {addProductToCart}; 