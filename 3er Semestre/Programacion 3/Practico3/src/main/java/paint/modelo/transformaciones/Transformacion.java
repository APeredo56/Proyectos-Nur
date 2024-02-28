package paint.modelo.transformaciones;

import paint.modelo.Imagen;

import javax.swing.*;

public abstract class Transformacion {
    protected Imagen imagen;

    public abstract void transformar();

}
