package Modelo;

import javax.swing.*;
import java.awt.*;


public class Barras extends JLabel{

    int x;
    int y;
    int ancho = 8;
    int alto;
    int espacioEntreBarras = 5;
    int espacioOcupado = ancho + espacioEntreBarras;

    public Barras (int posicion, int y, int alto){
        this.x = posicion * espacioOcupado;
        this.y = y;
        this.alto = alto;

        setBounds(this.x,this.y,this.ancho,this.alto);
        setBackground(Color.RED);
        setOpaque(true);
    }

    public int getAlto() {
        return alto;
    }

    public void setX(int xNuevo){
        this.setBounds(xNuevo, y,ancho,alto);
    }

    public int getEspacioOcupado() {
        return espacioOcupado;
    }

}
