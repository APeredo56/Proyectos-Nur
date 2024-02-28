package Ventana;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private PanelPrincipal panel;


    public VentanaPrincipal (){
        setSize(1200,750);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);


        panel = new PanelPrincipal();

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        VentanaPrincipal p = new VentanaPrincipal();
    }
}
