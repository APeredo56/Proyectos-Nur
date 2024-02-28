package figuras;

import java.awt.*;

public class Triangulo extends Figura {

    public Triangulo() {
    }

    public Triangulo(int x, int y) {
        super(x, y);
        color = Color.green.darker();
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        int[] puntos_x = {x, x + (tamaño / 2), x + tamaño};
        int[] puntos_y = {y + tamaño, y, y + tamaño};
        g.fillPolygon(puntos_x, puntos_y, 3);
    }
}
