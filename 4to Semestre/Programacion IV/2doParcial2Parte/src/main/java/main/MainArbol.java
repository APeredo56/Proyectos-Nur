package main;

import abb.ArbolBinarioBusqueda;
import avl.ArbolAvl;
import base.AbbAbstracto;

import java.util.Arrays;
import java.util.List;

public class MainArbol {

    public static void main(String[] args) {
        AbbAbstracto<Integer> arbol = new ArbolAvl<>();
        //arbol.insertar(26, 12, 38, 8, 18, 29 ,42, 10, 15 ,32, 30,35);

        arbol.insertar(38, 50, 80, 90, 85);

        List<Integer> elementos = arbol.getRecorridoInOrder();
        System.out.println(Arrays.toString(elementos.toArray()));

        elementos = arbol.getRecorridoPreOrder();
        System.out.println(Arrays.toString(elementos.toArray()));
        //PRE - ORDER: 26   12   8   10  18   15   38   29   32   30   35   42
        //PRE - ORDER: 50  38  85  80  90

        elementos = arbol.getRecorridoPostOrder();
        System.out.println(Arrays.toString(elementos.toArray()));
        //POST - ORDER: 10  8  15 18  12  30  35  32 29  42  38  26
        //POST - ORDER: 38  80  90  85  50

        System.out.println();
        System.out.println("Elimino el 50");
        arbol.eliminar(50);
        elementos = arbol.getRecorridoPreOrder();
        System.out.println(Arrays.toString(elementos.toArray()));

//        arbol.borrar();
//        elementos = arbol.getRecorridoInOrder();
//        System.out.println(Arrays.toString(elementos.toArray()));

//        System.out.println("Se encuentra 0: " + arbol.seEncuentra(0));
//        System.out.println("Se encuentra 38: " + arbol.seEncuentra(38));
//        System.out.println("Se encuentra 20: " + arbol.seEncuentra(20));
//        System.out.println("Se encuentra 35: " + arbol.seEncuentra(35));
//        System.out.println("Se encuentra 42: " + arbol.seEncuentra(42));
//        System.out.println("Se encuentra 8: " + arbol.seEncuentra(8));

        System.out.println();

        int mayor = arbol.getMayor();
        System.out.println("Mayor: " + mayor);

        int menor = arbol.getMenor();
        System.out.println("Menor: " + menor);
    }
}