package Inventario.Models;

import Inventario.Observer.Subscriber;

public class OrdenCompra implements Subscriber {
    Producto producto;

    public OrdenCompra(Producto producto){
        this.producto = producto;
    }

    @Override
    public void update(Inventario context) {
        context.productos.add(producto);
        System.out.println("Se compro el producto: " + producto);
        context.mostrarInventario();
    }

    public void confirmar(){
        this.update(Inventario.getInstance());
    }
}
