package Autos.Estacionamientos;

import Autos.Models.Auto;

public class EstacionamientoVIP extends Estacionamiento{
    public EstacionamientoVIP(Estacionamiento next) {
        super(next);
    }

    @Override
    public void handle(Auto auto) {
        if (auto.getConductor().isVip()){
            System.out.println(auto + " admitido en el Estacionamiento VIP");
        }
    }
}
