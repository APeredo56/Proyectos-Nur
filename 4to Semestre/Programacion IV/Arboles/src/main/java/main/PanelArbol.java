package main;

import abb.ArbolBinarioBusqueda;

import javax.swing.*;
import java.awt.*;

public class PanelArbol extends JPanel {
    private ArbolBinarioBusqueda arbolBinario;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        arbolBinario.dibujar(g, getWidth(), 0,0);
    }

    public ArbolBinarioBusqueda getArbolBinario() {
        return arbolBinario;
    }

    public void setArbolBinario(ArbolBinarioBusqueda arbolBinario) {
        this.arbolBinario = arbolBinario;
    }
}
