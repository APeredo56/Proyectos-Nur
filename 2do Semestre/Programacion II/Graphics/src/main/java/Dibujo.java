import figuras.Circulo;
import figuras.Figura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Dibujo extends JPanel {


    private ArrayList<Figura> lista=new ArrayList<>();



    public Dibujo(Integer x, Integer y){
        lista.add(new Circulo(x,y));
        setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //modificar el codigo para mostrar todos los elementos de la lista
        for (Figura elemento: lista) {
            elemento.dibujar(g);
        }
    }

    //agregamos un nuevo elemento
    public void addElemento(int x,int y){
        lista.add(new Circulo(x,y));
        repaint();
    }

    public void addElemento(Figura fig){
        fig.setX(fig.getX()-(fig.getTamaño()/2));
        fig.setY(fig.getY()-(fig.getTamaño()/2));
        lista.add(fig);
        repaint();
    }

    public ArrayList<Figura> getLista() {
        return lista;
    }
}
