import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Ventana extends JFrame implements MouseListener {
    private int jugador1 = 0;
    private int jugador2 = 0;
    private int x;
    private int y;
    private int turno = 0;

    private JPanel panelTablero = new JPanel();
    private JLabel lbTablero = new JLabel();
    private JLabel lbJugador1 = new JLabel("Jugador 1: " + jugador1);
    private JLabel lbJugador2 = new JLabel("Jugador 2: " + jugador2);
    private JLabel lbTurno = new JLabel("Turno del jugador " + (turno + 1));
    private JButton btnReincio = new JButton("Reiniciar partida");
    private ArrayList<Marcas> lista = new ArrayList<>();




    public Ventana() {
        setTitle("Tres en raya");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        btnReincio.addActionListener(e -> {
            limpiarTablero();
        });
        cargarElementos();

        //lbTablero.addMouseListener(this);
        setVisible(true);

    }

    public void cargarElementos() {
        x = 15;
        y = 100;
        lbTablero.setIcon(new ImageIcon("ImagenTablero.jpg"));
        lbTablero.setBounds(50, 50, 500, 500);
        lbJugador1.setBounds(50,470,100,50);
        lbJugador2.setBounds(340,470,100,50);
        btnReincio.setBounds(160,470, 140,50);
        lbTurno.setBounds(175,50,150,50);
        for (int i = 0; i < 9; i++) {
            Marcas lbEspacio = new Marcas(x, y);
            lbEspacio.setName("");
            lbEspacio.addMouseListener(this);
            lista.add(lbEspacio);
            lbTablero.add(lbEspacio);
            x += 125;
            if (x > 265) {
                x = 15;
                y += 100;
            }
        }
        panelTablero.setBounds(50, 0, 500, 500);
        panelTablero.setLayout(null);
        lbTablero.setLayout(null);
        panelTablero.add(lbTablero);
        add(lbJugador1);
        add(lbJugador2);
        add(btnReincio);
        add(lbTurno);
        add(panelTablero);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Marcas aux = (Marcas) e.getComponent();
        if (aux.getTurno() != 3) {
            aux.setTurno(turno);
            aux.marcarMovimiento();
            if (turno == 0) {
                turno += 1;
            } else {
                turno -= 1;
            }
        }
        verificarGanador();
        lbTurno.setText("Turno del jugador " + (turno + 1));
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void limpiarTablero() {
        for (Marcas aux : lista) {
            aux.setIcon(null);
            aux.setName("");
            aux.setTurno(0);
        }
    }

    public void mensajeVictoria(int jugador) {
        if (jugador == 1) {
            JOptionPane.showMessageDialog(null, "El jugador 1 gana");
        } else {
            JOptionPane.showMessageDialog(null, "El jugador 2 gana");
        }
    }

    public void verificarGanador() {
        String pos1 = lista.get(0).getName();
        String pos2 = lista.get(1).getName();
        String pos3 = lista.get(2).getName();
        String pos4 = lista.get(3).getName();
        String pos5 = lista.get(4).getName();
        String pos6 = lista.get(5).getName();
        String pos7 = lista.get(6).getName();
        String pos8 = lista.get(7).getName();
        String pos9 = lista.get(8).getName();
        if ((pos1.equals("X") && pos2.equals("X") && pos3.equals("X")) ||
                (pos4.equals("X") && pos5.equals("X") && pos6.equals("X")) ||
                (pos7.equals("X") && pos8.equals("X") && pos9.equals("X")) ||
                (pos1.equals("X") && pos4.equals("X") && pos7.equals("X")) ||
                (pos2.equals("X") && pos5.equals("X") && pos8.equals("X")) ||
                (pos3.equals("X") && pos6.equals("X") && pos9.equals("X")) ||
                (pos1.equals("X") && pos5.equals("X") && pos9.equals("X")) ||
                (pos3.equals("X") && pos5.equals("X") && pos7.equals("X"))) {
            jugador1 += 1;
            mensajeVictoria(1);
            limpiarTablero();
            lbJugador1.setText("Jugador 1: " + jugador1);
        }
        if ((pos1.equals("O") && pos2.equals("O") && pos3.equals("O")) ||
                (pos4.equals("O") && pos5.equals("O") && pos6.equals("O")) ||
                (pos7.equals("O") && pos8.equals("O") && pos9.equals("O")) ||
                (pos1.equals("O") && pos4.equals("O") && pos7.equals("O")) ||
                (pos2.equals("O") && pos5.equals("O") && pos8.equals("O")) ||
                (pos3.equals("O") && pos6.equals("O") && pos9.equals("O")) ||
                (pos1.equals("O") && pos5.equals("O") && pos9.equals("O")) ||
                (pos3.equals("O") && pos5.equals("O") && pos7.equals("O"))) {
            jugador2 += 1;
            mensajeVictoria(2);
            limpiarTablero();
            lbJugador2.setText("Jugador 2: " + jugador2);
        }


    }


    public static void main(String[] args) {
        new Ventana();
    }
}
