package logica;

import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import java.awt.*;

public class Bloque extends JLabel {

    private int x;
    private int y;
    private int ancho = 30;
    private int alto =100;

    private Color color;

    public Bloque(){

    }
    public Bloque(int x, int y, int ancho, int alto,Color color) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.color = color;
        setOpaque(true);
        setBackground(color);
        setBounds(x,y,ancho,alto);
    }

    public boolean colision(Caballo caballo){
         //return caballo.getX() + caballo.getTamaño() > x;
        Rectangle limite= this.getBounds();
        Rectangle limite_caballo=caballo.getBounds();
        return limite.intersects(limite_caballo);
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

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
