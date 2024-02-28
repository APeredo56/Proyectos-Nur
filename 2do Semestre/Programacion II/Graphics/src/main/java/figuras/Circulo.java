package figuras;

import java.awt.*;

public class Circulo extends Figura{

    public Circulo() {
    }

    public Circulo(int x, int y) {
        super(x, y);
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.fillOval(x,y,tamaño,tamaño);
    }
}
