package Naves;

import javax.swing.*;
import java.awt.*;

public class NaveJugador extends JLabel {

    public enum SENTIDO {ARRIBA, ABAJO, DERECHA, IZQUIERDA}

    private int tamaño = 50;
    private int vidas = 3;
    private int x;
    private int y;

    public NaveJugador (int x, int y){
        this.x = x;
        this.y = y;
        this.setBounds(this.x, this.y, tamaño,tamaño);
        ImageIcon imagen = new ImageIcon("naveJugador.png");
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
        this.setIcon(icono);
    }

    public void mover (SENTIDO sentido){
        switch (sentido){
            case ARRIBA -> this.y -= 25;
            case ABAJO -> this.y += 25;
            case DERECHA -> this.x += 25;
            case IZQUIERDA -> this.x -= 25;
        }
        this.setBounds(x,y,tamaño,tamaño);
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getTamaño() {
        return tamaño;
    }
}
