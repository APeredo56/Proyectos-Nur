package Autos.Estacionamientos;

import Autos.Models.Auto;

public class EstacionamientoCompacto extends Estacionamiento {

    public EstacionamientoCompacto(Estacionamiento next) {
        super(next);
    }

    @Override
    public void handle(Auto auto) {
        if (auto.getLargo() < 4.5 || auto.getLargo() > 5) {
            next.handle(auto);
            return;
        } else if (auto.getAncho() < 2.5 || auto.getAncho() > 2.7) {
            next.handle(auto);
            return;
        } else if (auto.getAltura() < 2.1 || auto.getAltura() > 2.3) {
            next.handle(auto);
            return;
        }
        System.out.println(auto + " admitido en el Estacionamiento Compacto");
    }
}
