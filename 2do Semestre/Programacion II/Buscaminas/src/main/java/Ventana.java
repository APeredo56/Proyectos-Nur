import javax.swing.*;

public class Ventana extends JFrame {
    private JButton btnReinicio = new JButton("Reiniciar");
    private Tablero tablero;
    private JLabel lbMinas;

    public Ventana(){
        cargarComponentes();
    }

    public void cargarComponentes(){
        setSize(600,600);
        setTitle("Buscaminas");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        tablero = new Tablero(16,12);

        tablero.setBounds(0,100,tablero.getAncho(),tablero.getAlto());
        btnReinicio.setBounds(0,0,100,40);

        btnReinicio.addActionListener(e -> {
            tablero.reiniciarTablero();
        });
        lbMinas = tablero.getLbMinas();
        lbMinas.setBounds(120,0,100,40);

        add(tablero);
        add(btnReinicio);
        add(lbMinas);

        setVisible(true);
    }

    public static void main(String[] args) {
        Ventana v = new Ventana();
    }
}
