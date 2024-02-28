import figuras.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Ventana extends JFrame implements MouseListener, KeyListener {

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
    Figura figuraSeleccionada = null;
    int posicionLista;
    int contador = 0;

    public Ventana() {
        setTitle("Mi Frame - Graphics");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setLayout(new BorderLayout());
        cargarPanelOpciones();
        this.addKeyListener(this);
        panel_principal.addMouseListener(this);
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
        this.add(panel_opciones, BorderLayout.WEST);
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
        for (Figura aux : panel_principal.getLista()) {
            if (e.getX() == aux.getX() && e.getY() == aux.getY()) {
                posicionLista = contador;
            }
            contador ++;
        }
    }

        @Override
        public void mouseEntered (MouseEvent e){

        }

        @Override
        public void mouseExited (MouseEvent e){

        }

        @Override
        public void keyTyped (KeyEvent e){

        }

        @Override
        public void keyPressed (KeyEvent e){
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                panel_principal.getLista().get(contador).mover(Figura.movimiento.DERECHA);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                panel_principal.getLista().get(contador).mover(Figura.movimiento.IZQUIERDA);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                panel_principal.getLista().get(contador).mover(Figura.movimiento.ARRIBA);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                panel_principal.getLista().get(contador).mover(Figura.movimiento.ABAJO);
            }
            //JOptionPane.showMessageDialog(null, panel_principal.getLista().get(contador).getX() + "");
        }

        @Override
        public void keyReleased (KeyEvent e){
            JOptionPane.showMessageDialog(null, "prueba");
            System.out.println("prueba");
            this.setFocusable(true);
        }
    }
