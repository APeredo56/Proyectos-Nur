package figuras;

import java.awt.*;

public abstract class Figura {
    protected int x;
    protected int y;
    protected int tamaño;
    protected Color color;
    protected boolean estaBloqueada = false;

    public enum DIRECCION {DERECHA, IZQUIERDA, ARRIBA, ABAJO}

    ;


    public Figura() {

    }

    public Figura(int x, int y) {
        this.x = x;
        this.y = y;
        this.tamaño = 100;
        this.color = Color.red;
    }

    public Figura(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.tamaño = 100;
        this.color = color;
    }


    public void mover(int ancho, int alto) {
        if (x > 0 && x + tamaño < ancho && y > 0 && y + tamaño < alto){
            mover(DIRECCION.DERECHA);
        }
    }

    public void mover(DIRECCION sentido) {
        switch (sentido) {
            case DERECHA -> x += 5;
            case IZQUIERDA -> x -= 5;
            case ARRIBA -> y -= 5;
            case ABAJO -> y += 5;
        }
        /*switch (sentido) {
            case DERECHA:
                x += 5;
                break;
            case IZQUIERDA:
                x -= 5;
                break;
            case ARRIBA:
                y -= 5;
                break;
            case ABAJO:
                y += 5;
                break;
        }*/
    }
    public void bloquear(){
        this.estaBloqueada = true;
    }

    public boolean isEstaBloqueada() {
        return estaBloqueada;
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

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void dibujar(Graphics g);
}
