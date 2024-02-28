import Mapa.Mapa;
import Naves.NaveEnemiga;
import Naves.NaveJugador;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private Mapa panelPrincipal;
    private JPanel panelInicio;
    private JLabel lbTitulo = new JLabel("Space Invaders");
    private JButton btIniciar = new JButton("Iniciar Partida");

    public Ventana () {
        setTitle("Space Invaders");
        setResizable(false);
        this.setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        panelInicio = new JPanel();
        cargarPanelInicio();
        setVisible(true);
        }

    public void cargarPanelInicio(){
        panelInicio.setBackground(Color.black);
        panelInicio.setLayout(null);
        lbTitulo.setBounds(450,200,500,200);
        lbTitulo.setForeground(Color.orange);
        lbTitulo.setFont(new Font("Arial",Font.BOLD,65));
        btIniciar.setBounds(600,450,150,60);
        btIniciar.setBackground(Color.gray.darker());
        btIniciar.setForeground(Color.white);
        btIniciar.setBorder(null);
        btIniciar.setFont(new Font("Arial", Font.PLAIN, 20));
        panelInicio.add(lbTitulo);
        panelInicio.add(btIniciar);
        int x = 60;
        for (int i = 0; i < 13; i++) {
            NaveEnemiga nave = new NaveEnemiga(x, 70);
            panelInicio.add(nave);
            x += 100;
        }
        NaveJugador nave2 = new NaveJugador(650,550);
        panelInicio.add(nave2);
        btIniciar.addActionListener(e -> {
            this.remove(panelInicio);
            panelPrincipal = new Mapa();
            add(panelPrincipal);
            Thread hilo = new Thread(panelPrincipal);
            panelPrincipal.cargarElementos();
            hilo.start();
            panelPrincipal.setFocusable(true);
            panelPrincipal.requestFocus();
            panelPrincipal.addKeyListener(panelPrincipal);
            repaint();
            this.setVisible(true);
            panelPrincipal.getBtSalir().addActionListener(e1 -> {
                panelPrincipal.terminarPartida();
                this.remove(panelPrincipal);
                this.add(panelInicio);
                repaint();
            });
        });
        add(panelInicio);
    }


    public static void main(String[] args) {
        Ventana v = new Ventana();
    }
}
