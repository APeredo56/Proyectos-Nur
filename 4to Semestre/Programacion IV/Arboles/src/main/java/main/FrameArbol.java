package main;

import javax.swing.*;

public class FrameArbol extends JFrame {

    PanelArbol panel = new PanelArbol();

    public FrameArbol(){
        setSize(600,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        add(panel);
    }

    public PanelArbol getPanel() {
        return panel;
    }
}
