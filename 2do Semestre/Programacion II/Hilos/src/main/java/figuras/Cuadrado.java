package figuras;

import java.awt.*;

public class Cuadrado extends Figura{

    public Cuadrado() {
    }

    public Cuadrado(int x, int y) {
        super(x, y);
        this.color=Color.blue;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.fillRect(x,y,tamaño,tamaño);
    }
}
