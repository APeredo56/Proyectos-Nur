package abb;

import base.NodoAbstracto;

import java.util.List;

class Nodo<E> extends NodoAbstracto<E> {

    private E dato;

    private Nodo<E> izquierdo;
    private Nodo<E> derecho;
    static int ancho = 50;

    public Nodo(E dato){
        this.dato = dato;
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public Nodo<E> getIzquierdo() {
        return izquierdo;
    }

    protected void setIzquierdo(Nodo<E> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo<E> getDerecho() {
        return derecho;
    }

    protected void setDerecho(Nodo<E> derecho) {
        this.derecho = derecho;
    }
}
