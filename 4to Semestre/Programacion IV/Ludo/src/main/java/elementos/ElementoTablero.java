package elementos;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class ElementoTablero implements Serializable {
    protected int posX;
    protected int posY;
    protected int size;
    protected Color color;
    public static final int bordes = 1;

    public ElementoTablero(int posX, int posY, int size, Color color) {
        this.posX = posX;
        this.posY = posY;
        this.size = size;
        this.color = color;
    }

    public ElementoTablero(Color color) {
        this.color = color;
    }

    public abstract void dibujar (Graphics g);

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
