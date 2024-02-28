package modelo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Explorador extends JFrame {
    private JMenuBar barraBotones = new JMenuBar();
    private JButton btnSubir = new JButton("Subir Archivo");
    private JButton btnCrear = new JButton("Crear Carpeta");
    private JButton btnAtras = new JButton("<-");

    private JLabel lbCarpetaActual = new JLabel(" Carpeta Actual: ");
    private PanelArchivos panel = new PanelArchivos(lbCarpetaActual);
    private JPanel panelMenu = new JPanel();
    private JFrame frameDialogo;
    private JButton btnAceptar = new JButton("Aceptar");
    private JLabel lbDialogo = new JLabel();
    private JTextField txtDialogo = new JTextField();

    public Explorador (){
        setSize(700,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        lbCarpetaActual.setBorder(new LineBorder(Color.black,2));
        lbCarpetaActual.setLayout(new BorderLayout());
        lbCarpetaActual.setFont(new Font("a",0,15));

        panelMenu.setLayout(new BorderLayout());
        panelMenu.add(barraBotones, BorderLayout.NORTH);
        panelMenu.add(lbCarpetaActual, BorderLayout.SOUTH);

        add(panelMenu, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        barraBotones.add(btnAtras);
        barraBotones.add(btnSubir);
        barraBotones.add(btnCrear);

        btnAtras.addActionListener(e -> accionAtras());
        btnCrear.addActionListener(e -> accionCrear());
        btnSubir.addActionListener(e -> accionSubir());

        lbDialogo.setBounds(10,10,200,30);
        txtDialogo.setBounds(10,50,260,30);
        btnAceptar.setBounds(110,90,80,30);
        btnAceptar.addActionListener(e -> {
            if (lbDialogo.getText().contains("carpeta")){
                panel.crearCarpeta(txtDialogo.getText());
            } else {
                panel.subirArchivo(txtDialogo.getText());
            }
            txtDialogo.setText("");
            frameDialogo.dispose();
        });

        setVisible(true);
    }

    public void accionSubir(){
        cargarVentanaDialogo("Archivo");
    }

    public void accionCrear(){
        cargarVentanaDialogo("Carpeta");
    }

    public void accionAtras(){
        panel.retrocederCarpeta();
    }

    public void cargarVentanaDialogo(String elemento){
        frameDialogo = new JFrame();
        frameDialogo.setLayout(null);
        frameDialogo.setSize(300,180);
        frameDialogo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frameDialogo.setLocationRelativeTo(null);
        frameDialogo.add(lbDialogo);
        frameDialogo.add(txtDialogo);
        frameDialogo.add(btnAceptar);
        if (elemento.equals("Carpeta")){
            lbDialogo.setText("Ingrese el nombre de la carpeta");
        } else {
            lbDialogo.setText("Ingrese la ruta del archivo");
        }
        frameDialogo.setVisible(true);
    }


}
