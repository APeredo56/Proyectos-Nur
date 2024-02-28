import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Ventana extends JFrame implements MouseListener {

    private JLabel etiqueta = new JLabel("¿ Boton ?");

    public Ventana() {
        setTitle("MouseListener");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        prepararElemento();
        setVisible(true);

    }

    private void prepararElemento() {
        setLayout(null);
        etiqueta.setBounds(250, 150, 150, 100);
        etiqueta.setOpaque(true);
        etiqueta.setBackground(Color.CYAN);
        etiqueta.setHorizontalTextPosition(SwingConstants.CENTER);
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        etiqueta.addMouseListener(this);
        etiqueta.setBorder(new BasicBorders.ButtonBorder(Color.darkGray, Color.black, Color.lightGray, Color.white));
        add(etiqueta);
    }


    public static void main(String[] args) {
        new Ventana();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        etiqueta.setBackground(Color.green.darker());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON3) {
            etiqueta.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(null, ""+e.getXOnScreen()+" - "+e.getYOnScreen());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        etiqueta.setBackground(Color.GREEN);
        int x = (int) (Math.random()*650 + 1);
        int y = (int) (Math.random()*450 + 1);
        etiqueta.setBounds(x, y, 150,100);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        etiqueta.setBackground(Color.CYAN);
    }
}
