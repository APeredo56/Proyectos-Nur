import java.awt.*;

public class Figura {

    public static enum movimiento {DERECHA, IZQUIERDA, ARRIBA, ABAJO}

    ;

    private int x;
    private int y;
    private int tamaño;
    private Color color;


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

    public void mover(movimiento mov){
        switch (mov){
            case DERECHA:
                x+=5;
                break;
            case IZQUIERDA:
                x-=5;
                break;
            case ABAJO:
                y+=5;
                break;
            case ARRIBA:
                y-=5;
                break;
        }
    }
    public void dibujar(Graphics g){
        g.setColor(color);
        g.fillOval(x,y,tamaño,tamaño);

    }
}
