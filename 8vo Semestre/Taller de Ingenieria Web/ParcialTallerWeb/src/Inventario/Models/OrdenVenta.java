package Inventario.Models;

import Inventario.Observer.Subscriber;

public class OrdenVenta implements Subscriber {
    Producto producto;

    public OrdenVenta(Producto producto){
        this.producto = producto;
    }

    @Override
    public void update(Inventario context) {
        context.productos.remove(producto);
        System.out.println("Se vendio el producto: " + producto);
        context.mostrarInventario();
    }

    public void confirmar(){
        this.update(Inventario.getInstance());
    }
}
