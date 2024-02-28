package paneles;

import Main.Ludo;
import jugador.Jugador;
import lombok.extern.slf4j.Slf4j;
import sockets.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Slf4j
public class MenuInicio extends JPanel {

    JTextField txtNombre = new JTextField();
    JComboBox comboBox = new JComboBox();
    JButton button = new JButton();
    JLabel lbListo = new JLabel("Empezar partida", SwingConstants.CENTER);
    private ImageIcon imagen;
    private JLabel lbFondo;

    public MenuInicio (){
        imagen = new ImageIcon("src\\imagenes\\fondo unirse.png");
        lbFondo = new JLabel(imagen);
        lbFondo.setBounds(0,0, getWidth(), getHeight());
        init();
    }

    public void init(){
        this.setLayout(null);
        int ancho = 100;
        int alto = 30;
        int separador = 40;



        comboBox.addItem("Rojo");
        comboBox.addItem("Verde");
        comboBox.addItem("Amarillo");
        comboBox.addItem("Azul");
        comboBox.setMaximumSize(new Dimension(ancho, alto));

        add(comboBox);
        add(txtNombre);

        button.setBackground(Color.RED);
        button.setIcon(new ImageIcon("src\\imagenes\\btn unirse.png"));
        add(button);



        lbListo.setOpaque(true);
        lbListo.setMaximumSize(new Dimension(lbListo.getFontMetrics(lbListo.getFont()).stringWidth(lbListo.getText())+10,alto));
        lbListo.setBackground(Color.red);
        JLabel finalLabel = lbListo;

        Cliente cliente = new Cliente();
        button.addActionListener(e -> {
            cliente.start(txtNombre.getText(), comboBox.getSelectedItem().toString());
            new Thread(() -> {
                try {
                    cliente.getOut().println("Agregar");
                    String mensaje = cliente.getIn().readLine();
                    if (mensaje.equals("Valido")) {
                        //desactivar los elementos y avisar que esta esperando
                        cliente.setEnSala(true);
                        txtNombre.setEnabled(false);
                        comboBox.setEnabled(false);
                        button.setEnabled(false);
                        add(lbListo);
                        new Thread(() -> {
                            String empezar = null;
                            try {
                                empezar = cliente.getIn().readLine();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                            if (empezar.equals("Iniciar Partida")){
                                try {
                                    ObjectInputStream o = new ObjectInputStream(new DataInputStream(cliente.getClientSocket().getInputStream()));
                                    Jugador j = (Jugador) o.readObject();
                                    cliente.setJugador(j);
                                } catch (IOException | ClassNotFoundException ioException) {
                                    ioException.printStackTrace();
                                }
                                Ludo ventana = (Ludo) this.getTopLevelAncestor();
                                ventana.add(cliente.getJugador().getPartida());
                                ventana.remove(this);

                            }
                        }).start();
                    } else {
                        JOptionPane.showMessageDialog(null, mensaje);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }).start();
        });

        lbListo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (finalLabel.getBackground() == Color.red){
                    finalLabel.setBackground(Color.green);
                    cliente.getOut().println("Listo");
                } else {
                    finalLabel.setBackground(Color.red);
                    cliente.getOut().println("Esperar");
                }
            }
        });
        add(lbFondo);
    }

    public void paint(Graphics g) {
        super.paint(g);
        lbFondo.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        lbFondo.setBounds(0,0, getWidth(), getHeight());
        add(lbFondo);
        txtNombre.setBounds(263,235,170,35);
        comboBox.setBounds(500,235,170,35);
        lbListo.setBounds(380,290,170,35);
        button.setBounds(380,380,150,60);

    }

}