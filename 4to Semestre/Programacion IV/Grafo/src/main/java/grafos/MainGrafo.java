package grafos;

import diccionario.ComparadorGenerico;

public class MainGrafo {

    public static void main(String[] args) {
        Grafo<String, Integer> grafo = new Grafo<>(new ComparadorGenerico<>());

        grafo.insertarVertice("UNO", 1);
        grafo.insertarVertice("DOS", 2);
        grafo.insertarVertice("TRES", 3);
        grafo.insertarVertice("CUATRO", 4);
        grafo.insertarVertice("CINCO", 5);

        grafo.insertarArista("DOS", "UNO");
        grafo.insertarArista("DOS", "TRES");
        grafo.insertarArista("DOS", "CUATRO");
        grafo.insertarArista("CUATRO", "DOS");
        grafo.insertarArista("CUATRO", "UNO");
        grafo.insertarArista("CINCO", "UNO");
        grafo.insertarArista("DOS", "CINCO");
        grafo.insertarArista("UNO", "UNO");
        grafo.insertarArista("UNO", "DOS");


        String grafoStr = grafo.toString();
        System.out.println(grafoStr);
        System.out.println();
//        grafo.eliminarArista("CUATRO", "DOS");
//        grafo.eliminarArista("DOS", "CUATRO");
//        grafoStr = grafo.toString();
//        System.out.println(grafoStr);
    }
}
