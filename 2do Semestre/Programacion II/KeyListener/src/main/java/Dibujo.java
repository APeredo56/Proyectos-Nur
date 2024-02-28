import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Dibujo extends JPanel implements KeyListener{

    private ArrayList<Figura> lista = new ArrayList<>();
    private Figura elemento;
    private int figuraSeleccionada = 0;

    public Dibujo(Integer x, Integer y) {
        lista.add(new Figura(x, y));
        setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //modificar el codigo para mostrar todos los elementos de la lista
        for(Figura elemento: lista){
            elemento.dibujar(g);
        }

    }
    public void addElemento (Figura fig){
        fig.setX(fig.getX()-(fig.getTamaño()/2));
        fig.setY(fig.getY()-(fig.getTamaño()/2));
        lista.add(fig);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                getLista().get(figuraSeleccionada).mover(Figura.movimiento.DERECHA);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            getLista().get(figuraSeleccionada).mover(Figura.movimiento.IZQUIERDA);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            getLista().get(figuraSeleccionada).mover(Figura.movimiento.ARRIBA);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            getLista().get(figuraSeleccionada).mover(Figura.movimiento.ABAJO);
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    //agregamos un nuevo elemento

    public ArrayList<Figura> getLista() {
        return lista;
    }

    public int getFiguraSeleccionada() {
        return figuraSeleccionada;
    }

    public void setFiguraSeleccionada(int figuraSeleccionada) {
        this.figuraSeleccionada = figuraSeleccionada;
    }
}
