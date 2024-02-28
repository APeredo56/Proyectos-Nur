import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Carretera extends JPanel implements KeyListener, Runnable {

    private Frogger personaje;
    private ArrayList<Vehiculo> trafico = new ArrayList<>();


    public Carretera() {
    }

    public void cargarElementos() {
        this.personaje = new Frogger(this.getHeight() - 6, this.getWidth());
        int vehiculos = (this.getHeight() / 30) - 2;
        int posicion = 30;
        Random aleatorio = new Random();
        for (int i = 0; i < vehiculos; i++) {

            if (aleatorio.nextInt(100) > 50) {
                trafico.add(new Vehiculo(aleatorio.nextInt(5) + 1, posicion, aleatorio.nextInt(5) + 1));
            } else {
                trafico.add(new Vehiculo(aleatorio.nextInt(5) + 1, posicion,
                        aleatorio.nextInt(5) + 1, Vehiculo.SENTIDO.IZQUIERDA, this.getWidth()));
            }
            posicion += 30;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Vehiculo auto : trafico) {
            auto.pintar(g);
        }
        personaje.pintar(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            if (personaje.getPosicion().x - personaje.getTamaño() > 0)
                this.personaje.mover(Frogger.SENTIDO.IZQUIERDA);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            if (personaje.getPosicion().x + personaje.getTamaño()*2 < this.getWidth())
                this.personaje.mover(Frogger.SENTIDO.DERECHA);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            if (personaje.getPosicion().y - personaje.getTamaño() > 0)
                this.personaje.mover(Frogger.SENTIDO.ARRIBA);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            if (personaje.getPosicion().y + personaje.getTamaño()*2 < this.getHeight())
                this.personaje.mover(Frogger.SENTIDO.ABAJO);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(100);
                this.repaint();
                for (Vehiculo auto : trafico) {
                    auto.mover();

                    if (auto.getPosicion().x + auto.getTamaño() < 0) {
                        auto.setPosicionX(this.getWidth());
                    }
                    if (auto.getPosicion().x > this.getWidth()) {
                        auto.setPosicionX(0 - auto.getTamaño());
                    }
                    if (auto.colision(personaje)) {
                        return;
                    }
                }
                this.repaint();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
