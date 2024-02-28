package base;

public abstract class NodoAbstracto<E> {

    protected E dato;

    public abstract NodoAbstracto<E> getDerecho();

    public abstract NodoAbstracto<E> getIzquierdo();

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }
}
