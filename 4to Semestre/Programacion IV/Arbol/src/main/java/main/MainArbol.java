package main;

import abb.ArbolBinarioBusqueda;

import java.util.Arrays;
import java.util.List;

public class MainArbol {

    public static void main(String[] args) {
        /*ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<>();
        arbol.insertar(26, 12, 38, 8, 18, 29 ,42, 10, 15 ,32, 30,35);

        List<Integer> elementos = arbol.getRecorridoInOrder();
        System.out.println(Arrays.toString(elementos.toArray()));

        elementos = arbol.getRecorridoPreOrder();
        System.out.println(Arrays.toString(elementos.toArray()));
        //PRE - ORDER: 26   12   8   10  18   15   38   29   32   30   35   42

        //POST - ORDER: 10  8  15 18  12  30  35  32 29  42  38  26
        elementos = arbol.getRecorridoPostOrder();
        System.out.println(Arrays.toString(elementos.toArray()));

        arbol.borrar();
        elementos = arbol.getRecorridoInOrder();
        System.out.println(Arrays.toString(elementos.toArray()));*/
        FrameArbol f = new FrameArbol();
    }
}
