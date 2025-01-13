package Autos.Estacionamientos;

import Autos.Models.Auto;

public class EstacionamientoNormales extends Estacionamiento {
    public EstacionamientoNormales(Estacionamiento next) {
        super(next);
    }

    @Override
    public void handle(Auto auto) {
        if (auto.getLargo() < 5.5 || auto.getLargo() > 6) {
            next.handle(auto);
            return;
        } else if (auto.getAncho() < 2.7 || auto.getAncho() > 3) {
            next.handle(auto);
            return;
        } else if (auto.getAltura() < 2.3 || auto.getAltura() > 2.5) {
            next.handle(auto);
            return;
        }
        System.out.println(auto + " admitido en el Estacionamiento Normales");
    }
}
