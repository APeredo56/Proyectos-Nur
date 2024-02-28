import java.awt.*;
import java.security.PublicKey;

public class Frogger {

    public enum sentido {DERECHA, IZQUIERDA, ARRIBA, ABAJO}

    private Point posicion;
    private int tamaño;
    private int vidas;
    private int puntos;


    public Frogger(int alto, int ancho) {
        tamaño = 25;
        vidas = 3;
        posicion=new Point();
        posicion.y = alto - tamaño;
        posicion.x = ancho / 2 - (tamaño / 2);
    }

    public void mover(SENTIDO posicion) {
        switch (posicion) {
            case ABAJO -> this.posicion.y += tamaño+5;
            case ARRIBA -> this.posicion.y -= tamaño+5;
            case DERECHA -> this.posicion.x += tamaño+5;
            case IZQUIERDA -> this.posicion.x -= tamaño+5;
        }
    }

    public void pintar(Graphics g){
        g.setColor(Color.green.darker());
        g.fillOval(posicion.x, posicion.y,tamaño,tamaño);
    }

    public Point getPosicion() {
        return posicion;
    }

    public void setPosicion(Point posicion) {
        this.posicion = posicion;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
