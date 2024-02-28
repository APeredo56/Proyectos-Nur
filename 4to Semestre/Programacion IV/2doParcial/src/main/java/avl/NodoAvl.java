package avl;

import base.NodoAbstracto;

public class NodoAvl<E> extends NodoAbstracto<E> {

    private NodoAvl<E> izquierdo;
    private NodoAvl<E> derecho;

    private int cantidadNiveles;

    public NodoAvl(E dato) {
        this.dato = dato;
        izquierdo = null;
        derecho = null;
    }

    @Override
    public NodoAvl<E> getDerecho() {
        return derecho;
    }

    @Override
    public NodoAvl<E> getIzquierdo() {
        return izquierdo;
    }

    protected void setIzquierdo(NodoAvl<E> izquierdo) {
        this.izquierdo = izquierdo;
    }

    protected void setDerecho(NodoAvl<E> derecho) {
        this.derecho = derecho;
    }

    protected int getCantidadNiveles() {
        return cantidadNiveles;
    }

    protected void setCantidadNiveles(int cantidadNiveles) {
        this.cantidadNiveles = cantidadNiveles;
    }
}
