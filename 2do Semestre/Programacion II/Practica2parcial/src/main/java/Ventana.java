import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ventana extends JFrame implements KeyListener {

    private Caballo jugador1 = new Caballo(20,50, Color.red, 100);
    private Caballo jugador2 = new Caballo(20, 200, Color.blue, 100 );
    private Bloque meta = new Bloque(500, 0,50,400);

    public Ventana(){
        setTitle("Carrera");
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        add(jugador1);
        add(jugador2);
        add(meta);
        addKeyListener(this);
        setVisible(true);

    }

    public static void main(String[] args) {
        Ventana V = new Ventana ();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 65) {
            jugador1.pasoIzquierdo();
        }
        if (e.getKeyCode() == 68){
            jugador1.pasoDerecho();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            jugador2.pasoDerecho();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            jugador2.pasoIzquierdo();
        }
        if (meta.colision(jugador1)){
            JOptionPane.showMessageDialog(null, "El Jugador 1 es el ganador");
        }
        if (meta.colision(jugador2)){
            JOptionPane.showMessageDialog(null, "El Jugador 2 es el ganador");
        }
    }
}
