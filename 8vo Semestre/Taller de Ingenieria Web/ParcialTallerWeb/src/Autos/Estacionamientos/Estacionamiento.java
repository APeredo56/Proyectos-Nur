package Autos.Estacionamientos;

import Autos.Models.Auto;

public abstract class Estacionamiento {
    Estacionamiento next;

    public Estacionamiento(Estacionamiento next) {
        this.next = next;
    }

    public abstract void handle(Auto auto);
}
