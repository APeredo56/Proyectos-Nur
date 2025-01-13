package Autos.Estacionamientos;

import Autos.Models.Auto;

public class EstacionamientoGrandes extends Estacionamiento {
    public EstacionamientoGrandes(Estacionamiento next) {
        super(next);
    }

    @Override
    public void handle(Auto auto) {
        if (auto.getLargo() < 6 || auto.getLargo() > 7) {
            next.handle(auto);
            return;
        } else if (auto.getAncho() < 3 || auto.getAncho() > 3.5) {
            next.handle(auto);
            return;
        } else if (auto.getAltura() < 2.5 || auto.getAltura() > 2.8) {
            next.handle(auto);
            return;
        }
        System.out.println(auto + " admitido en el Estacionamiento Grandes");
    }
}
