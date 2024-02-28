package logica;

import javax.swing.*;
import java.awt.*;

public class Caballo extends JLabel {
    private boolean paso;
    private int x;
    private int y;
    private int tamaño = 150;

    private Color color;

    public Caballo(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        setBounds(x,y,tamaño,tamaño);
    }

    public void pasoIzquierdo() {
        if (paso) {
            x += 5;
            paso = !paso;
            setBounds(x,y,tamaño,tamaño);
        }
    }

    public void pasoDerecho() {
        if (!paso) {
            x += 5;
            paso = !paso;
            setBounds(x,y,tamaño,tamaño);
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
