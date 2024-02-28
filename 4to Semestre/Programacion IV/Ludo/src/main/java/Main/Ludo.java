package Main;

import paneles.MenuInicio;

import javax.swing.*;

public class Ludo extends JFrame {

    public Ludo() {
        setSize(960, 540);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //add(new PanelInicio());
        add(new MenuInicio());
        setVisible(true);
    }

    public static void main(String[] args) {
        Ludo ludo = new Ludo();
    }
}
