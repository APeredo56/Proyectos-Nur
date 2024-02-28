package logica;

import javax.swing.*;
import java.awt.*;

public class Bloque {

    private int x;
    private int y;
    private int ancho = 30;
    private int alto =100;

    private Image imagen;

    public Bloque(){

    }
    public Bloque(int x, int y, int ancho, String imagen) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.imagen = new ImageIcon(imagen).getImage();
    }

    public boolean colision(Caballo caballo){
        // return caballo.getX() + caballo.getTamaño() > x;
        Rectangle limite=new Rectangle(x,y,ancho,alto);
        Rectangle limite_caballo=new Rectangle(caballo.getX(),caballo.getY(),caballo.getTamaño(),caballo.getTamaño());
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

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = new ImageIcon(imagen).getImage();
    }
}
