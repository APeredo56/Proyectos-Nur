package Naves;

import javax.swing.*;
import java.awt.*;

public class Potenciadores extends JLabel {
    private int y;
    private int tamaño = 30;

    public Potenciadores (int x, int y){
        this.y = y;
        setBounds(x, y, tamaño,tamaño);
        ImageIcon imagen = new ImageIcon("Potenciador.png");
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
        this.setIcon(icono);
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTamaño() {
        return tamaño;
    }
}
