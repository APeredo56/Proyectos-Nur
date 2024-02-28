package grafica;

import logica.Bloque;
import logica.Caballo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Dibujo extends JPanel implements KeyListener {

    private Caballo jugador1=new Caballo(10,10, "jugador1.png");
    private Caballo jugador2=new Caballo(10,150,"jugador2.jpg");

    private Bloque meta=new Bloque();

    public Dibujo(){
        setBackground(Color.white);
        meta.setImagen("meta.jpg");
        meta.setAncho(30);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        meta.setX(getWidth()-70);
        meta.setAlto(this.getHeight());
        g.drawImage(meta.getImagen(),getWidth()-80,0,getWidth()-20,getHeight(),0,0,30,300,null);

        g.drawImage(jugador1.getImagen(),jugador1.getX(),jugador1.getY(),null);
        g.drawImage(jugador2.getImagen(),jugador2.getX(),jugador2.getY(),null);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
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
}
