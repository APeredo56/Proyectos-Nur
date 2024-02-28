package Ventana;

import Modelo.Arbol;
import Modelo.ListaArboles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PanelPrincipal extends JPanel implements MouseListener, PropertyChangeListener {
    private ListaArboles listaArboles;
    private Arbol arbol;
    private final static Logger logger = LogManager.getLogger(PanelPrincipal.class);

    public PanelPrincipal(){
        setLayout(null);
        setBackground(Color.white);
        setOpaque(true);
        setPreferredSize(new Dimension(1200,750));

        listaArboles  = new ListaArboles();
        this.addMouseListener(this);
        listaArboles.addObserver(this);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(9,176,225));
        g.fillRect(0,0,1200,250);
        g.setColor(Color.yellow);
        g.fillOval(-100,-100,200,200);
        g.setColor(new Color(102,204,0));
        g.fillRect(0,250,1200,500);

        for (Arbol aux:listaArboles) {
            aux.dibujar(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getY() < 250) {
            return;
        }
        int complejidad;
        if (e.getY() > 588) {
            complejidad = 4;
        } else {
            if (e.getY() > 476) {
                complejidad = 3;
            } else {
                if (e.getY() > 364) {
                    complejidad = 2;
                } else {
                    complejidad = 1;
                }
            }
        }
        Point puntoInicial = new Point(e.getX(), e.getY());

        arbol = new Arbol(puntoInicial, 5, complejidad);
        listaArboles.agregarArbol(arbol);
        logger.info("Se creo un arbol en la posicion X: " + puntoInicial.x + ", Y:" + puntoInicial.y + ", de complejidad: "
                + (complejidad) + ". Total de arboles: " + listaArboles.size());


    }

    @Override
    public void mouseReleased(MouseEvent e) {


    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
