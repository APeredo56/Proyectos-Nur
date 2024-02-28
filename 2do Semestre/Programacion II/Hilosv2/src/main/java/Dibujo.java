import figuras.Circulo;
import figuras.Cuadrado;
import figuras.Figura;
import figuras.Triangulo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Dibujo extends JPanel implements KeyListener, Runnable {


    private ArrayList<Figura> lista = new ArrayList<>();
    private Figura seleccionado;

    public Dibujo(Integer x, Integer y) {
        lista.add(new Circulo(x, y));
        setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //modificar el codigo para mostrar todos los elementos de la lista
        for (Figura elemento : lista) {
            elemento.dibujar(g);
        }
        if (lista.size() >= 2) {
            Figura ultimoElemento = lista.get(lista.size() - 1);
            Figura penultimoElemento = lista.get(lista.size() - 2);
            g.drawLine(ultimoElemento.getX() + ultimoElemento.getTamaño()/2, ultimoElemento.getY() +
                    ultimoElemento.getTamaño()/2, penultimoElemento.getX() + penultimoElemento.getTamaño()/2,
                    penultimoElemento.getY() + penultimoElemento.getTamaño()/2);
        }
    }

    //agregamos un nuevo elemento
    public void addElemento(int x, int y) {
        lista.add(new Circulo(x, y));
        repaint();
    }

    public void addElemento(Figura fig) {
        fig.setX(fig.getX() - (fig.getTamaño() / 2));
        fig.setY(fig.getY() - (fig.getTamaño() / 2));
        lista.add(fig);
        repaint();
    }

    public void seleccionarElemento(int x, int y) {
        for (Figura aux : lista) {
            if (aux.getX() < x && aux.getX() + aux.getTamaño() > x &&
                    aux.getY() < y && aux.getY() + aux.getTamaño() > y) {
                if (seleccionado != null) {
                    if (seleccionado instanceof Circulo) {
                        seleccionado.setColor(Color.red);
                    }
                    if (seleccionado instanceof Cuadrado) {
                        seleccionado.setColor(Color.BLUE);
                    }
                    if (seleccionado instanceof Triangulo) {
                        seleccionado.setColor(Color.green.darker());
                    }
                }
                seleccionado = aux;
                seleccionado.setColor(Color.blue);
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //seleccionado.mover(this.getWidth(), this.getHeight());
        if (e.getKeyCode() == KeyEvent.VK_B){
            seleccionado.bloquear();
        }
        if (!seleccionado.isEstaBloqueada()) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (seleccionado.getX() > 0)
                    seleccionado.mover(Figura.DIRECCION.IZQUIERDA);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (seleccionado.getX() + seleccionado.getTamaño() < this.getWidth())
                    seleccionado.mover(Figura.DIRECCION.DERECHA);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (seleccionado.getY() > 0)
                    seleccionado.mover(Figura.DIRECCION.ARRIBA);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (seleccionado.getY() + seleccionado.getTamaño() < this.getHeight())
                    seleccionado.mover(Figura.DIRECCION.ABAJO);
            }
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        try {
            while (true) {
                if (seleccionado != null) {
                    seleccionado.mover(this.getWidth(), this.getHeight());
                }
                repaint();
                Thread.sleep(30);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
