import java.awt.*;

public class Vehiculo {


    public enum SENTIDO {DERECHA, IZQUIERDA}

    private Point posicion;
    private int tamaño;
    private Color color;
    private SENTIDO sentido;
    private int velocidad;


    public Vehiculo() {
        tamaño = 1;
        color = Color.red;
        sentido = SENTIDO.DERECHA;
        posicion = new Point();
        posicion.y = 50;
        posicion.x = 0;
        velocidad = 3;
    }

    public Vehiculo(int tamaño, int posicion, int velocidad) {
        this.tamaño = tamaño;
        sentido = SENTIDO.DERECHA;
        this.posicion = new Point();
        this.posicion.y = posicion;
        this.velocidad = velocidad;
        color = Color.red;
    }
    public Vehiculo(int tamaño, int posicion, int velocidad, SENTIDO sentido, int posicionX) {
        this.tamaño = tamaño;
        this.posicion = new Point();
        this.posicion.y = posicion;
        this.posicion.x=posicionX;
        this.sentido = sentido;
        this.velocidad = velocidad;
        color = Color.red;
    }

    public void mover() {
        switch (sentido) {
            case DERECHA -> this.posicion.x += velocidad;
            case IZQUIERDA -> this.posicion.x -= velocidad;
        }
    }

    public void pintar(Graphics g) {
        g.setColor(color);
        g.fillRect(posicion.x, posicion.y, tamaño * 25, 25);
    }
    public boolean colision(Frogger personaje){
        Rectangle area_personaje=new Rectangle(personaje.getPosicion().x,
                personaje.getPosicion().y,personaje.getTamaño(),personaje.getTamaño());
        Rectangle area_vehiculo=new Rectangle(posicion.x,posicion.y,tamaño*25,25);
        return area_vehiculo.intersects(area_personaje);
    }

    public Point getPosicion() {
        return posicion;
    }

    public void setPosicion(Point posicion) {
        this.posicion = posicion;
    }
    public void setPosicionX(int posicion) {
        this.posicion.x = posicion;
    }

    public int getTamaño() {
        return tamaño*25;
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

    public SENTIDO getSentido() {
        return sentido;
    }

    public void setSentido(SENTIDO sentido) {
        this.sentido = sentido;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
}
