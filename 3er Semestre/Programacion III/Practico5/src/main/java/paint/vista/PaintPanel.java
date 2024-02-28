package paint.vista;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import paint.listas.Lista;
import paint.modelo.Imagen;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PaintPanel extends JPanel implements PropertyChangeListener, MouseListener, MouseMotionListener {
    private final Imagen modelo;
    private Imagen imagenRecortada;
    int[][] nuevosPixeles;

    private JLabel bordes;
    private int anchoRecorte;
    private int altoRecorte;

    private Point inicio = null;
    private Point inicioPixeles = new Point(0, 0);

    private final Logger logger = LogManager.getLogger(PaintPanel.class);

    public PaintPanel(Imagen m) {
        setLayout(null);
        modelo = m;
        modelo.addObserver(this);

        bordes = new JLabel();
        bordes.setBorder(new LineBorder(Color.red, 3));
        bordes.setBackground(null);
        bordes.setBounds(0, 0, 0, 0);

        add(bordes);
        addMouseListener(this);
        addMouseMotionListener(this);

    }

    @Override
    public Dimension getPreferredSize() {

        return new Dimension(600, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (modelo == null) {
            return;
        }

        BufferedImage rsm = new BufferedImage(modelo.getAncho(), modelo.getAlto(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rsm.createGraphics();

        modelo.dibujar(g2d);
        g.drawImage(rsm, 0, 0, null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName() != "IMAGEN") {
            return;
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        inicio = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        imagenRecortada = new Imagen(anchoRecorte, altoRecorte);
        nuevosPixeles = new int[anchoRecorte][altoRecorte];
        for (int j = 0; j < altoRecorte; j++) {
            for (int i = 0; i < anchoRecorte; i++) {
                nuevosPixeles[i][j] = modelo.getPixeles()[inicioPixeles.x + i][inicioPixeles.y + j];
            }
        }
        imagenRecortada.setPixeles(nuevosPixeles);
        logger.info("Se selecciono un recorte de la imagen en la posicion: " + inicioPixeles.x + ", " + inicioPixeles.y
        + "; de ancho: " + anchoRecorte + " y altura: " + altoRecorte);
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //si se lleva el mouse hacia abajo a la derecha despues de presionar
        if (inicio.y < e.getY() && inicio.x < e.getX()) {
            altoRecorte = e.getY() - inicio.y;
            anchoRecorte = e.getX() - inicio.x;
            bordes.setBounds(inicio.x, inicio.y, anchoRecorte, altoRecorte);
            inicioPixeles = inicio;
        } else {
            //si se lleva el mouse hacia arriba a la derecha despues de presionar
            if (inicio.y > e.getY() && inicio.x < e.getX()) {
                altoRecorte = inicio.y - e.getY();
                anchoRecorte = e.getX() - inicio.x;
                bordes.setBounds(inicio.x, inicio.y - altoRecorte, anchoRecorte, altoRecorte);
            } else {
                //si se lleva el mouse hacia abajo a la izquierda despues de presionar
                if (inicio.y < e.getY() && inicio.x > e.getX()) {
                    altoRecorte = e.getY() - inicio.y;
                    anchoRecorte = inicio.x - e.getX();
                    bordes.setBounds(inicio.x - anchoRecorte, inicio.y, anchoRecorte, altoRecorte);
                } else {
                    //si se lleva el mouse hacia arriba a la izquierda despues de presionar
                    altoRecorte = inicio.y - e.getY();
                    anchoRecorte = inicio.x - e.getX();
                    bordes.setBounds(inicio.x - anchoRecorte, inicio.y - altoRecorte, anchoRecorte, altoRecorte);
                }
            }
            inicioPixeles.setLocation(bordes.getX(), bordes.getY());
        }
        //Si se pasa de los limites de la imagen
        if (bordes.getX() + bordes.getWidth() > modelo.getAncho()) {
            bordes.setBounds(bordes.getX(), bordes.getY(), modelo.getAncho() - bordes.getX(), bordes.getHeight());
            anchoRecorte = bordes.getWidth();
        }
        if (bordes.getY() + bordes.getHeight() > modelo.getAlto()) {
            bordes.setBounds(bordes.getX(), bordes.getY(), bordes.getWidth(), modelo.getAlto() - bordes.getY());
            altoRecorte = bordes.getHeight();

        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public Imagen getImagenRecortada() {
        return imagenRecortada;
    }

    public void setImagenRecortada(Imagen imagenRecortada) {
        this.imagenRecortada = imagenRecortada;
    }

    public Point getInicioPixeles() {
        return inicioPixeles;
    }

    public int getAltoRecorte() {
        return altoRecorte;
    }

    public int getAnchoRecorte() {
        return anchoRecorte;
    }

    public JLabel getBordes() {
        return bordes;
    }

    public Logger getLogger() {
        return logger;
    }
}
