package paint.modelo.transformaciones;

import paint.modelo.Imagen;

import javax.swing.*;

public abstract class Transformacion {
    protected Imagen imagen;
    protected int [][] pixelesOriginales;
    protected int [][] pixelesCambiados;

    public abstract void transformar();

    public int[][] getPixelesOriginales() {
        return pixelesOriginales;
    }

    public int[][] getPixelesCambiados() {
        return pixelesCambiados;
    }
}
