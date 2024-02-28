package paneles;

import jugador.Jugador;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Partida extends JPanel {
    Tablero tablero;
    JPanel panelIzquierdo = new JPanel();
    JPanel panelDerecho = new JPanel();
    PanelJugador panelJugador;

    public Partida (ArrayList<Jugador> jugadores){
        tablero = new Tablero(jugadores);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        tablero.setAlignmentX(Component.CENTER_ALIGNMENT);
        tablero.setMinimumSize(new Dimension(400,400));
        tablero.setPreferredSize(new Dimension(400,400));

        panelDerecho.setLayout(new GridLayout(2,1));
        panelDerecho.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panelIzquierdo.setLayout(new GridLayout(2,1));
        panelDerecho.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel panelRojo = new JPanel();
        panelRojo.setLayout(new BorderLayout());
        panelIzquierdo.add(panelRojo);

        JPanel panelAzul = new JPanel();
        panelAzul.setLayout(new BorderLayout());
        panelIzquierdo.add(panelAzul);

        JPanel panelVerde = new JPanel();
        panelVerde.setLayout(new BorderLayout());
        panelDerecho.add(panelVerde);

        JPanel panelAmarillo = new JPanel();
        panelAmarillo.setLayout(new BorderLayout());
        panelDerecho.add(panelAmarillo);


        for (Jugador aux : jugadores) {
            panelJugador = new PanelJugador(aux);
            if(aux.getColor() == Color.red){
                panelRojo.add(panelJugador);
            } else if (aux.getColor() == Color.green){
                panelVerde.add(panelJugador);
            } else if (aux.getColor() == Color.yellow){
                panelAmarillo.add(panelJugador);
            } else if (aux.getColor() == Color.blue){
                panelAzul.add(panelJugador);
            }
        }


        add(panelIzquierdo);
        add(tablero);
        add(panelDerecho);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int sizeTablero = this.getHeight();
        int sizePaneles = (this.getWidth() - sizeTablero)/2;
        panelIzquierdo.setPreferredSize(new Dimension(sizePaneles, this.getHeight()));
        tablero.setPreferredSize(new Dimension(sizeTablero, sizeTablero));
        panelDerecho.setPreferredSize(new Dimension(sizePaneles, this.getHeight()));
    }
}
