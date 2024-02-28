package main;

import abb.ArbolBinarioBusqueda;

import javax.swing.*;

public class PanelOperaciones extends JPanel {
    private JButton btnInsertar = new JButton();
    private JTextField txtInsertar = new JTextField();
    private JTextField txtMostrar = new JTextField();
    private ArbolBinarioBusqueda<Integer> arbol;

    public PanelOperaciones(ArbolBinarioBusqueda<Integer> arbol){
        this.arbol = arbol;
        txtInsertar.setBounds(0,0,100,100);
    }
}
