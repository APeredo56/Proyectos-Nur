package main;

import abb.ArbolBinarioBusqueda;

import javax.swing.*;
import java.awt.*;

public class PanelArbol extends JPanel {
    ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<>();

    public PanelArbol(){
        arbol.insertar(28,45,6,3,7,5,23,56,43,21,53,3,17);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        arbol.dibujar(g, this.getWidth(), 0,10);
    }
}
