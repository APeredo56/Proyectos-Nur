package grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import logica.Bloque;
import logica.Caballo;

public class Ventana extends JFrame implements KeyListener {

    private Caballo jugador1=new Caballo(00,0, Color.red);
    private Caballo jugador2=new Caballo(10,150,Color.blue);

    private Bloque meta=new Bloque(270,0,30,300,Color.DARK_GRAY);


    public Ventana(){
        super("Parcial 2 - Pro 2");
        setSize(800,330);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        jugador1.setIcon(new ImageIcon("jugador1.jpg"));
        add(jugador1);
        jugador2.setIcon(new ImageIcon("jugador2.jpg"));
        add(jugador2);
        meta.setIcon(new ImageIcon("meta.jpg"));
        add(meta);
        addKeyListener(this);
        setVisible(true);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_A){
            jugador1.pasoIzquierdo();
        }
        if(e.getKeyCode()==KeyEvent.VK_D){
            jugador1.pasoDerecho();
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            jugador2.pasoIzquierdo();
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            jugador2.pasoDerecho();
        }
        if(meta.colision(jugador1)){
            JOptionPane.showMessageDialog(null,"GANO EL JUGADOR 1");
        }
        if(meta.colision(jugador2)){
            JOptionPane.showMessageDialog(null,"GANO EL JUGADOR 2");
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    public static void main(String[] args) {
        new Ventana();
    }
}
