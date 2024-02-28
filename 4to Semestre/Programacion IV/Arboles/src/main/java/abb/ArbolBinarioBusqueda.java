package abb;

import base.AbbAbstracto;
import base.NodoAbstracto;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ArbolBinarioBusqueda<E> extends AbbAbstracto<E> {

    private Nodo<E> raiz;

    public ArbolBinarioBusqueda(Comparator<E> comparator) {
        super(comparator);
        raiz = null;
    }

    public ArbolBinarioBusqueda() {
        super();
        raiz = null;
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



    public void eliminar(E dato){
        raiz = eliminar(raiz, dato);
    }

    private Nodo<E> eliminar(Nodo<E> rsa, E dato) {

        if(rsa == null){
            return rsa;
        }

        int comparacion = comparar(dato, rsa.getDato());
        if(comparacion == 0){
            //Aqui encontramos el elemento que queremos eliminar

            //Cuando es hoja
            if(esHoja(rsa)){
                return null;
            }

            //Cuando le falta el hijo izquierdo
            if(rsa.getIzquierdo() == null){
                return rsa.getDerecho();
            }

            //Cuando le falta el hijo derecho
            if(rsa.getDerecho() == null){
                return rsa.getIzquierdo();
            }

            //Cuando tiene ambos hijos
            E mayorSI = getMayor(rsa.getIzquierdo());
            rsa.setDato(mayorSI);

            Nodo<E> nuevaReferencia = eliminar(rsa.getIzquierdo(), mayorSI);
            rsa.setIzquierdo(nuevaReferencia);

        }else if(comparacion > 0){
            Nodo<E> nuevaReferencia = eliminar(rsa.getDerecho(), dato);
            rsa.setDerecho(nuevaReferencia);

        }else{
            Nodo<E> nuevaReferencia = eliminar(rsa.getIzquierdo(), dato);
            rsa.setIzquierdo(nuevaReferencia);
        }
        return rsa;

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

    public int getPeso(){
        if (raiz == null){
            return 0;
        }
        return getPesoNodo(raiz);
    }

    public int getPesoNodo(Nodo<E> nodo){
        return calcularPesoNodo(nodo);
    }

    private int calcularPesoNodo(Nodo<E> nodoActual){
        if (nodoActual == null){
            return 0;
        }
        return 1 + calcularPesoNodo(nodoActual.getIzquierdo()) +
                calcularPesoNodo(nodoActual.getDerecho());

    }

    public int getAltura(){
        if (raiz == null){
            return 0;
        }
        return calcularAltura(raiz);
    }

    private int calcularAltura(Nodo<E> nodoActual){
        if (nodoActual == null)
            return 0;

        int alturaDerecho = calcularAltura(nodoActual.getDerecho());
        int alturaIzquierdo = calcularAltura(nodoActual.getIzquierdo());
        return 1 + Math.max(alturaDerecho, alturaIzquierdo);
    }

    public List<E> getRecorridoPorNiveles() {
        List<E> lista = new ArrayList<>();
        if (raiz != null) {
            Queue<Nodo<E>> colaNodos = new LinkedList<>();
            colaNodos.add(raiz);
            recorridoPorNiveles(lista, colaNodos);
        }
        return lista;
    }

    private void recorridoPorNiveles(List<E> lista, Queue<Nodo<E>> colaNodos) {
        if (colaNodos.isEmpty()) {
            return;
        }

        Nodo<E> nodo = colaNodos.poll();
        lista.add(nodo.getDato());

        if (nodo.getIzquierdo() != null) {
            colaNodos.add(nodo.getIzquierdo());
        }
        if (nodo.getDerecho() != null) {
            colaNodos.add(nodo.getDerecho());
        }

        recorridoPorNiveles(lista, colaNodos);
    }

    public List<E> getRamaMasLarga() {
        List<E> lista = new ArrayList<>();
        buscarRamaMasLarga(raiz, lista);
        return lista;
    }

    private void buscarRamaMasLarga(Nodo<E> actual, List<E> lista) {
        if (actual == null)
            return;
        lista.add(actual.getDato());

        int alturaSI = calcularAltura(actual.getIzquierdo());
        int alturaSD = calcularAltura(actual.getDerecho());

        if (alturaSI > alturaSD)
            buscarRamaMasLarga(actual.getIzquierdo(), lista);
        else
            buscarRamaMasLarga(actual.getDerecho(), lista);
    }

    public List<E> getNodosCompletos(){
        List<E> lista = new ArrayList<>();
        encontrarNodosCompletos(raiz, lista);
        return lista;
    }
    private void encontrarNodosCompletos(NodoAbstracto<E> actual, List<E> lista) {
        if (actual != null && actual.getIzquierdo() != null && actual.getDerecho() != null){
            System.out.println("Actual: " + actual.getDato() +  ", Derecho: " + actual.getDerecho().getDato()
            + ", Izquierdo: " + actual.getIzquierdo().getDato());
            lista.add(actual.getDato());
        }
        if(actual == null){
            return;
        }
        encontrarNodosCompletos(actual.getIzquierdo(), lista);
        encontrarNodosCompletos(actual.getDerecho(), lista);

    }

    public List<E> getNivel(int nivel){
        if (raiz == null){
            return null;
        }
        List<E> lista = new ArrayList<>();
        return buscarNivel(raiz, lista, 0, nivel);
    }

    private List<E> buscarNivel(Nodo<E> actual, List<E> lista, int nivelActual, int nivelLimite){
        if (nivelActual == nivelLimite){
            lista.add(actual.getDato());
        }

        if (actual.getIzquierdo() != null) {
            buscarNivel(actual.getIzquierdo(), lista, nivelActual + 1, nivelLimite);
        }
        if (actual.getDerecho() != null) {
            buscarNivel(actual.getDerecho(), lista, nivelActual + 1, nivelLimite);
        }

        return lista;
    }

    public List<E> getHijosIzquierdos(){
        List<E> lista = new ArrayList<>();
        encontrarIzquierdos(raiz, lista);
        return lista;
    }
    private void encontrarIzquierdos(NodoAbstracto<E> actual, List<E> lista) {
        if (actual != null && actual.getIzquierdo() != null){
            lista.add(actual.getIzquierdo().getDato());
        }
        if(actual == null){
            return;
        }
        encontrarIzquierdos(actual.getIzquierdo(), lista);
        encontrarIzquierdos(actual.getDerecho(), lista);

    }

    @Override
    protected void setRaiz(NodoAbstracto<E> raiz) {
        this.raiz = (Nodo<E>) raiz;
    }

    @Override
    public NodoAbstracto<E> getRaiz() {
        return raiz;
    }


    public E getAncestroComun(E dato1, E dato2){
        return obtenerAncentroComun(raiz, dato1, dato2).getDato();
    }
    private Nodo<E> obtenerAncentroComun (Nodo<E> actual, E dato1, E dato2){
        if (actual == null){
            return null;
        }

        if (actual.getDato() == dato1 || actual.getDato() == dato2){
            return actual;
        }
        Nodo<E> izquierdo = obtenerAncentroComun(actual.getIzquierdo(), dato1, dato2);
        Nodo<E> derecho = obtenerAncentroComun(actual.getDerecho(), dato1, dato2);
        if (izquierdo!= null && derecho != null){
            return actual;
        } else if (izquierdo == null && derecho == null){
            return null;
        } else {
            if (izquierdo == null){
                return derecho;
            } else {
                return izquierdo;
            }
        }
    }

    private boolean isPerfectRec(Nodo<E> actual, int d, int nivel)
    {
        // An empty tree is perfect
        if (actual == null)
            return true;

        // If leaf node, then its depth must be same as
        // depth of all other leaves.
        if (actual.getIzquierdo() == null && actual.getDerecho() == null)
            return (d == nivel+1);

        // If internal node and one child is empty
        if (actual.getIzquierdo() == null || actual.getDerecho() == null)
            return false;

        // Left and right subtrees must be perfect.
        return isPerfectRec(actual.getIzquierdo(), d, nivel+1) && isPerfectRec(actual.getDerecho(), d, nivel+1);
    }

    // Wrapper over isPerfectRec()
    public boolean isPerfect(Nodo<E> raiz)
    {
        int d = getAltura();
        return isPerfectRec(raiz, d, 0);
    }

}
