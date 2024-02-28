package main;

import abb.ArbolBinarioBusqueda;
import avl.ArbolAvl;

import javax.swing.*;
import java.awt.*;

public class PanelArbol extends JPanel {
    ArbolAvl<Integer> arbol = new ArbolAvl<>();

    public PanelArbol(){
        arbol.insertar(35,50,45,20,25,33, 32);
        arbol.eliminar(45);
        arbol.eliminar(50);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        arbol.dibujar(g, this.getWidth(), 0,10, arbol.getRaiz());
    }
}
