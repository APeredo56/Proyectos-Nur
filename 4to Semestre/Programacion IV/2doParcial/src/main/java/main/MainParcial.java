package main;

import abb.ArbolBinarioBusqueda;

import java.util.TreeSet;

public class MainParcial {


    public static void main(String[] args) {
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<>();
        arbol.insertar(39,3,5,65,32,12,58,85,43,72,15,31);

        System.out.println(arbol.prueba(43));

        TreeSet<Integer> ar = new TreeSet<>();
    }
}
