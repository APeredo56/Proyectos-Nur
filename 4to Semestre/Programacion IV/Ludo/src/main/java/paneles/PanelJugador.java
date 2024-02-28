package paneles;

import elementos.Dado;
import jugador.Jugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class PanelJugador extends JPanel implements Serializable {
    private JLabel lbNombre = new JLabel();
    private Dado dado = new Dado();
    private int giros = 0;
    private JTextField chat = new JTextField();
    private Jugador jugador;

    private transient ActionListener tirarDados = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dado.tirar();
            repaint();
            giros ++;
            if (giros == 10){
                timer.stop();
            }
        }
    };

    private Timer timer = new Timer(100, new TirarDados());



    public PanelJugador(Jugador jugador){
        setLayout(null);
        this.jugador = jugador;
        addMouseListener(new Listener());

        lbNombre.setBackground(Color.red);
        lbNombre.setText(jugador.getNombre());
        lbNombre.setOpaque(true);
        add(lbNombre);
        add(chat);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int alturaDado = this.getWidth()/2;
        int alturaNombre = this.getHeight()/6;
        lbNombre.setBounds(0,0, this.getWidth(), alturaNombre);
        dado.acomodar(0,getHeight()/6,alturaDado);
        chat.setBounds(0,alturaNombre + alturaDado, this.getWidth(), this.getHeight() -alturaDado - alturaNombre);

        dado.dibujar(g);
    }

    public class TirarDados implements ActionListener, Serializable {

        @Override
        public void actionPerformed(ActionEvent e) {
            dado.tirar();
            repaint();
            giros ++;
            if (giros == 10){
                timer.stop();
            }
        }
    }

    public class Listener extends MouseAdapter implements Serializable {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (dado.presionado(e.getPoint())){
                giros = 0;
                timer.start();
            }
        }
    }

}
