package Naves;

import javax.swing.*;
import java.awt.*;

public class Balas extends JLabel {
    private int x;
    private int y;
    private int alto = 30;
    private int ancho = 15;
    private boolean esDelJugador;

    public Balas (int x, int y, boolean esDelJugador){
        this.x = x - ancho/2;
        this.y = y - alto;
        this.setBounds(this.x,this.y,ancho,alto);
        ImageIcon imagen;
        if (esDelJugador) {
            imagen = new ImageIcon("balaJugador.png");
        } else {
            imagen = new ImageIcon("balaEnemigos.png");
        }
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(this.ancho,this.alto,Image.SCALE_DEFAULT));
        this.setIcon(icono);
        this.esDelJugador = esDelJugador;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
