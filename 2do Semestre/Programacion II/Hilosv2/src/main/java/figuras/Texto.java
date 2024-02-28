package figuras;

import java.awt.*;

public class Texto extends Figura{
    private String texto="Hola mundo";

    public Texto() {
    }

    public Texto(int x, int y) {
        super(x, y);
    }

    public Texto(int x, int y, String texto) {
        super(x, y);
        this.texto = texto;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.drawString(texto,x,y);
    }
}
