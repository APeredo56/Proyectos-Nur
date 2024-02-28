package abb;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArbolBinarioBusqueda<E> {

    private Nodo<E> raiz;
    private Comparator<E> comparator;

    public ArbolBinarioBusqueda(Comparator<E> comparator) {
        this();
        this.comparator = comparator;
    }

    public ArbolBinarioBusqueda() {
        raiz = null;
    }

    public void insertar(E... datos){
        for (E dato : datos) {
            insertar(dato);
        }
    }

    public void insertar(E dato){
        raiz = insertar(raiz, dato);
    }
    private Nodo<E> insertar(Nodo<E> rsa, E dato){
        if(rsa == null){
            rsa = new Nodo<>(dato);
        }else{
            int comparacion = comparar(dato, rsa.getDato());
            if(comparacion > 0){ // dato > rsa.getDato()

                //Insertamos a la derecha
                Nodo<E> recienInsertado = insertar(rsa.getDerecho(), dato);
                rsa.setDerecho(recienInsertado);

            }else if(comparacion < 0){ // dato < rsa.getDato()

                //Insertamos a la izquierda
                Nodo<E> recienInsertado = insertar(rsa.getIzquierdo(), dato);
                rsa.setIzquierdo(recienInsertado);

            }else{
                // dato ES IGUAL A rsa.getDato()
                // entonces no hago nada
            }
        }

        return rsa;
    }


    public List<E> getRecorridoInOrder(){
        List<E> list = new ArrayList<>();
        recorridoInOrder(raiz, list);
        return list;
    }

    private void recorridoInOrder(Nodo<E> rsa, List<E> list) {

        if(rsa == null){
            return;
        }

        recorridoInOrder(rsa.getIzquierdo(), list);
        list.add(rsa.getDato());
        recorridoInOrder(rsa.getDerecho(), list);

    }

    public List<E> getRecorridoPreOrder(){
        List<E> list = new ArrayList<>();
        recorridoPreOrder(raiz, list);
        return list;
    }

    private void recorridoPreOrder(Nodo<E> rsa, List<E> list) {

        if(rsa == null){
            return;
        }

        list.add(rsa.getDato());
        recorridoPreOrder(rsa.getIzquierdo(), list);
        recorridoPreOrder(rsa.getDerecho(), list);
    }

    public List<E> getRecorridoPostOrder(){
        List<E> list = new ArrayList<>();
        recorridoPostOrder(raiz, list);
        return list;
    }

    private void recorridoPostOrder(Nodo<E> rsa, List<E> list) {

        if(rsa == null){
            return;
        }

        recorridoPostOrder(rsa.getIzquierdo(), list);
        recorridoPostOrder(rsa.getDerecho(), list);
        list.add(rsa.getDato());
    }

    public void borrar(){
        raiz = null;
    }
    public int comparar(E dato1, E dato2){
        if (comparator != null){
            return comparator.compare(dato1, dato2);
        }
        if(dato1 instanceof Comparable && dato2 instanceof Comparable){
            return ((Comparable<E>) dato1).compareTo(dato2);
        }else{
            throw new ClassCastException("No se puede comparar");
        }
    }

    public void dibujar(Graphics g, int ancho, int x, int y){
        dibujar(g, ancho, x, y, raiz);
    }

    private void dibujar(Graphics g, int ancho, int xActual, int yActual, Nodo<E> nodoActual) {
        int mitad = ancho / 2;
        int padding = 18;
        g.drawOval(
                xActual + mitad - Nodo.ancho / 2,
                yActual,
                Nodo.ancho,
                Nodo.ancho
        );
        g.drawString(
                nodoActual.getDato().toString(),
                xActual + mitad - (Nodo.ancho / 2) + padding,
                yActual + Nodo.ancho / 2
        );
        if (nodoActual.getDerecho() != null){
            dibujar(g, mitad, xActual + mitad, (int) (yActual + Nodo.ancho*1.5), nodoActual.getDerecho());
            g.drawLine(xActual + mitad, yActual + Nodo.ancho, xActual + mitad + mitad/2,
                    (int) (yActual + Nodo.ancho*1.5));
        }
        if (nodoActual.getIzquierdo() != null){
            dibujar(g, mitad, xActual, (int) (yActual + Nodo.ancho*1.5), nodoActual.getIzquierdo());
            g.drawLine(xActual + mitad,yActual + Nodo.ancho, xActual + mitad/2,
                    (int) (yActual + Nodo.ancho*1.5));
        }
    }

}
