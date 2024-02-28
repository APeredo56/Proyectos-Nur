import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private JButton btnCronometro = new JButton("Cronometro");
    private JButton btnSemaforo = new JButton("Semaforo");
    private JButton btnIniciar = new JButton("Iniciar");
    private JButton btnReiniciar = new JButton("Reiniciar");
    private JButton btnDetener = new JButton("Detener");
    private JButton btnPreparado = new JButton("Preparado");
    private JLabel etiqueta = new JLabel("0,000");

    private JPanel panelPrincipal = new JPanel();

    private Cronometro cronometro;
    private Semaforo semaforo;
    private boolean isSemaforo = false;

    public Ventana (){
        setTitle("Cronometro - Semaforo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,350);
        setLocationRelativeTo(null);
        setLayout(null);
        cargarElementos();
        setVisible(true);
    }


    public void cargarElementos() {
        panelPrincipal.setLayout(null);
        panelPrincipal.setBounds(0,0,this.getWidth(),this.getHeight());
        JToolBar barra = new JToolBar("Opciones");
        barra.add(btnCronometro);
        barra.add(btnSemaforo);
        barra.setBounds(0,0,this.getWidth(),30);
        panelPrincipal.add(barra, BorderLayout.NORTH);
        etiqueta.setBounds(this.getWidth()/2 - 58,this.getHeight()/4, 100,50);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 32));
        btnIniciar.setBounds(etiqueta.getX(), etiqueta.getY() + 80, etiqueta.getWidth(), 50);
        btnIniciar.setBackground(Color.blue);
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 15));
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setOpaque(true);
        btnDetener.setBounds(etiqueta.getX(), etiqueta.getY() + 80, etiqueta.getWidth(), 50);
        btnDetener.setBackground(Color.blue);
        btnDetener.setFont(new Font("Arial", Font.BOLD, 15));
        btnDetener.setForeground(Color.WHITE);
        btnDetener.setOpaque(true);
        btnPreparado.setBounds(etiqueta.getX(), etiqueta.getY() + 80, etiqueta.getWidth()+30, 50);
        btnPreparado.setBackground(Color.lightGray);
        btnPreparado.setFont(new Font("Arial", Font.BOLD, 15));
        btnPreparado.setOpaque(true);
        btnReiniciar.setBounds(btnIniciar.getX(), btnIniciar.getY() + 50, btnIniciar.getWidth(),50);
        btnReiniciar.setBackground(Color.white);
        btnReiniciar.setBorderPainted(false);
        panelPrincipal.add(etiqueta);
        panelPrincipal.add(btnIniciar);
        panelPrincipal.add(btnReiniciar);
        btnCronometro.addActionListener(e -> {
            panelPrincipal.remove(semaforo);
            isSemaforo = false;
            panelPrincipal.add(btnReiniciar);
            panelPrincipal.add(btnIniciar);
            panelPrincipal.remove(btnDetener);
            repaint();
        });

        btnSemaforo.addActionListener(e -> {
            isSemaforo = true;
            semaforo = new Semaforo(0,40,this.getWidth(),50);
            panelPrincipal.add(semaforo);
            panelPrincipal.remove(btnReiniciar);
            repaint();
        });
        btnIniciar.addActionListener(e -> {
            if (!isSemaforo) {
                cronometro = new Cronometro(etiqueta);
                panelPrincipal.remove(btnIniciar);
                panelPrincipal.add(btnDetener);
                repaint();
            } else {
                panelPrincipal.remove(btnIniciar);
                panelPrincipal.add(btnPreparado);
                panelPrincipal.add(btnDetener);
                btnDetener.setVisible(false);
                cronometro = new Cronometro(etiqueta, semaforo, panelPrincipal);
                repaint();
            }
        });
        btnDetener.addActionListener(e -> {
            cronometro.detener();
        });
        btnReiniciar.addActionListener(e -> {
            if(cronometro.isParar()) {
                etiqueta.setText("0,000");
                this.remove(btnDetener);
                this.add(btnIniciar);
                repaint();
            }
        });
        add(panelPrincipal);
    }

    public static void main(String[] args) {
        new Ventana();

    }
}
