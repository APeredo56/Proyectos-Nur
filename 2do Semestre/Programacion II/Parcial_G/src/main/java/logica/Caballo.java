package logica;

import javax.swing.*;
import java.awt.*;

public class Caballo {
    private boolean paso;
    private int x;
    private int y;
    private int tamaño = 100;

    private Image imagen;

    public Caballo(int x, int y, String imagen) {
        this.x = x;
        this.y = y;
        this.imagen = new ImageIcon(imagen).getImage();
    }

    public void pasoIzquierdo() {
        if (paso) {
            x += 5;
            paso = !paso;
        }
    }

    public void pasoDerecho() {
        if (!paso) {
            x += 5;
            paso = !paso;
        }
    }

    public boolean isPaso() {
        return paso;
    }

    public void setPaso(boolean paso) {
        this.paso = paso;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
}
