package Inventario.Observer;

import Inventario.Models.Inventario;

public interface Subscriber {
    void update(Inventario context);
}
