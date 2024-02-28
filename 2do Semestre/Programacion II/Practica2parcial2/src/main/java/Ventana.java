import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private JLabel lbTexto = new JLabel("Texto");
    private JLabel lbX = new JLabel("X");
    private JLabel lbY = new JLabel("Y");
    private JLabel lbAncho = new JLabel("Ancho");
    private JLabel lbAlto = new JLabel("Alto");
    private JTextField txtTexto = new JTextField();
    private JTextField txtX = new JTextField();
    private JTextField txtY = new JTextField();
    private JTextField txtAncho = new JTextField();
    private JTextField txtAlto = new JTextField();

    private JPanel panelOpciones = new JPanel();

    public Ventana() {
        setTitle("Parcial 2");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cargarPanelOpciones();
        setVisible(true);
    }


    public void cargarPanelOpciones() {
        int x = 30;
        int y = 20;
        int ancho = 60;
        int alto = 20;
        panelOpciones.setLayout(null);
        lbTexto.setBounds(x, y, ancho, alto);
        y += 40;
        lbX.setBounds(x, y, ancho, alto);
        y += 40;
        lbY.setBounds(x, y, ancho, alto);
        y += 40;
        lbAncho.setBounds(x, y, ancho, alto);
        y += 40;
        lbAlto.setBounds(x, y, ancho, alto);
        y -= 160;
        x += 60;
        ancho += 20;
        txtTexto.setBounds(x, y, ancho, alto);
        y += 40;
        txtX.setBounds(x, y, ancho, alto);
        y += 40;
        txtY.setBounds(x, y, ancho, alto);
        y += 40;
        txtAncho.setBounds(x, y, ancho, alto);
        y += 40;
        txtAlto.setBounds(x, y, ancho, alto);

        panelOpciones.add(lbTexto);
        panelOpciones.add(lbX);
        panelOpciones.add(lbY);
        panelOpciones.add(lbAncho);
        panelOpciones.add(lbAlto);
        panelOpciones.add(txtTexto);
        panelOpciones.add(txtX);
        panelOpciones.add(txtY);
        panelOpciones.add(txtAncho);
        panelOpciones.add(txtAlto);
        JSeparator separator = new JSeparator();
        separator.setBounds(50,50,10,500);
        separator.setBackground(Color.BLACK);
        panelOpciones.add(separator);
        panelOpciones.setBackground(Color.LIGHT_GRAY);
        panelOpciones.setPreferredSize(new Dimension(200,500));
        this.add(panelOpciones, BorderLayout.WEST);
    }

    public static void main(String[] args) {
        new Ventana();
    }
}


