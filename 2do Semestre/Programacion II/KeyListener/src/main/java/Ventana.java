import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Ventana extends JFrame implements MouseListener {

    public Dibujo panel_principal = new Dibujo(20, 50);

    public Ventana() {
        setTitle("Mi Frame - Graphics");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setLayout(new BorderLayout());
        this.add(panel_principal, BorderLayout.CENTER);
        this.addKeyListener(panel_principal);
        panel_principal.addMouseListener(this);
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int posicionX;
        int posicionY;
        ArrayList<Figura> lista = panel_principal.getLista();
        if (e.getButton() == MouseEvent.BUTTON3) {
            Figura fig = new Figura(x, y);
            panel_principal.addElemento(fig);
        }
        if (e.getButton() == MouseEvent.BUTTON1) {
            for (int i = 0; i < lista.size(); i++) {
                posicionX = lista.get(i).getX();
                posicionY = lista.get(i).getY();
                panel_principal.getLista().get(i).setColor(Color.red);
                if (x <= posicionX + 100 && x >= posicionX && y <= posicionY + 100 && y >= posicionY) {
                    panel_principal.setFiguraSeleccionada(i);
                    panel_principal.getLista().get(i).setColor(Color.red.darker());
                    repaint();
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
