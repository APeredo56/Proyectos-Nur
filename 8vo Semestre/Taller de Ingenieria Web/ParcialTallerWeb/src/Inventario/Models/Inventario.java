package Inventario.Models;

import Inventario.Observer.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    List<Producto> productos = new ArrayList<>();
    List<Subscriber> subscribers = new ArrayList<>();
    private static Inventario instance;


    private Inventario(){}

    public static Inventario getInstance(){
        if (instance == null){
            instance = new Inventario();
        }
        return instance;
    }

    public void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber){
        subscribers.remove(subscriber);
    }

    public void mostrarInventario(){
        System.out.println("Mostrando inventario");
        for (Producto producto: productos){
            System.out.println(producto);
        }
    }
}
