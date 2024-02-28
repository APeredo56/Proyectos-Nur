package grafica;

import javax.swing.*;
import java.awt.*;
import logica.Caballo;

public class Ventana extends JFrame {

    private Dibujo panel_principal=new Dibujo();


    public Ventana(){
        super("Parcial 2 - Pro 2");
        setSize(800,330);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel_principal);
        addKeyListener(panel_principal);
        setVisible(true);
    }


    public static void main(String[] args) {
        new Ventana();
    }
}
