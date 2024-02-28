import figuras.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Ventana extends JFrame implements MouseListener {

    private JButton btnCuadrado = new JButton("Cuadrado");
    private JButton btnCirculo = new JButton("Circulo");
    private JButton btnTexto = new JButton("Texto");
    private JButton btnTriangulo = new JButton("Triangulo");

    private JButton btnArriba = new JButton("W");
    private JButton btnAbajo = new JButton("S");
    private JButton btnIzquierda = new JButton("A");
    private JButton btnDerecha = new JButton("D");
    private JButton btnPosicion = new JButton("Crear");
    private JTextField txtX = new JTextField();
    private JTextField txtY = new JTextField();
    private JLabel lbX = new JLabel("Posicion X:");
    private JLabel lbY = new JLabel("Posicion Y:");
    private JLabel lbTitulo = new JLabel("Tipo de elemento");

    public JPanel panel_opciones = new JPanel();

    private Integer x = 40;
    private Integer y = 20;
    private String tipo_figura;

    private Dibujo panel_principal = new Dibujo(x, y);


    public Ventana() {
        tipo_figura="Circulo";
        setTitle("Mi Frame - Graphics");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setLayout(new BorderLayout());
        cargarPanelOpciones();
        setVisible(true);


        btnCirculo.addActionListener(e -> {
            tipo_figura = "Circulo";
        });
        btnCuadrado.addActionListener(e -> {
            tipo_figura = "Cuadrado";
        });
        btnTriangulo.addActionListener(e -> {
            tipo_figura = "Triangulo";
        });
        btnTexto.addActionListener(e -> {
            tipo_figura = "Texto";
        });
        panel_principal.addMouseListener(this);

        this.addKeyListener(panel_principal);
        btnTexto.addKeyListener(panel_principal);
        btnCuadrado.addKeyListener(panel_principal);
        btnCirculo.addKeyListener(panel_principal);
        btnTriangulo.addKeyListener(panel_principal);
        txtX.addKeyListener(panel_principal);
        txtY.addKeyListener(panel_principal);

        Thread hilo=new Thread(panel_principal);
        hilo.start();
    }

    public void cargarPanelOpciones() {
        int y = 10;
        JSeparator separador = new JSeparator(SwingConstants.HORIZONTAL);
        panel_opciones.setLayout(null);
        lbTitulo.setBounds(10, y, 120, 30);
        y += 40;

        /*btnArriba.setBounds(50,y,40,40);
        y+=40;
        btnIzquierda.setBounds(10,y,40,40);
        btnAbajo.setBounds(50,y,40,40);
        btnDerecha.setBounds(90,y,40,40);*/
        separador.setBounds(5, y, 130, 10);
        y += 10;
        lbX.setBounds(10, y, 120, 24);
        y += 25;
        txtX.setBounds(10, y, 120, 24);
        y += 25;
        lbY.setBounds(10, y, 120, 24);
        y += 25;
        txtY.setBounds(10, y, 120, 24);
        y += 40;
        btnCirculo.setBounds(10, y, 120, 30);
        y += 40;
        btnCuadrado.setBounds(10, y, 120, 30);
        y += 40;
        btnTriangulo.setBounds(10, y, 120, 30);
        y += 40;
        btnTexto.setBounds(10, y, 120, 30);
        y += 40;
        btnPosicion.setBounds(10, y, 120, 24);
        //quitar margen de botones --> padding
        btnArriba.setMargin(new Insets(0, 0, 0, 0));
        btnAbajo.setMargin(new Insets(0, 0, 0, 0));
        btnIzquierda.setMargin(new Insets(0, 0, 0, 0));
        btnDerecha.setMargin(new Insets(0, 0, 0, 0));

        panel_opciones.add(lbTitulo);
        panel_opciones.add(btnCirculo);
        panel_opciones.add(btnCuadrado);
        panel_opciones.add(btnTexto);
        panel_opciones.add(btnTriangulo);
        /*panel_opciones.add(btnArriba);
        panel_opciones.add(btnAbajo);
        panel_opciones.add(btnIzquierda);
        panel_opciones.add(btnDerecha);*/
        panel_opciones.add(separador);
        panel_opciones.add(lbX);
        panel_opciones.add(lbY);
        panel_opciones.add(txtX);
        panel_opciones.add(txtY);
        //panel_opciones.add(btnPosicion);
        //panel_opciones.setBackground(Color.darkGray);
        panel_opciones.setPreferredSize(new Dimension(140, 130));
        //this.add(panel_opciones, BorderLayout.WEST);
        this.add(panel_principal, BorderLayout.CENTER);


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
        Figura fig = null;
        if(e.getButton() == MouseEvent.BUTTON1){
            panel_principal.seleccionarElemento(x,y);
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            if (tipo_figura.equals("Cuadrado")) {
                fig = new Cuadrado(x, y);
            } else if (tipo_figura.equals("Circulo")) {
                fig = new Circulo(x, y);
            } else if (tipo_figura.equals("Triangulo")) {
                fig = new Triangulo(x, y);
            } else {
                fig = new Texto(x, y);
            }
            panel_principal.addElemento(fig);
        }
        panel_principal.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
