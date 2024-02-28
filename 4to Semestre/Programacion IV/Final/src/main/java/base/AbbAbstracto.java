package base;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AbbAbstracto<E> {

    private Comparator<E> comparator;

    public AbbAbstracto(Comparator<E> comparator){
        this.comparator = comparator;
    }

    public AbbAbstracto(){

    }

    public abstract void insertar(E obj);

    public abstract void eliminar(E obj);

    protected abstract void setRaiz(NodoAbstracto<E> raiz);

    public abstract NodoAbstracto<E> getRaiz();


    public void insertar(E... datos){
        for (E dato : datos) {
            insertar(dato);
        }
    }

    public boolean seEncuentra(E dato){
        //Retornar true si el dato se encuentra en el Arbol, caso contrario retornar false
        return seEncuentra(getRaiz(), dato);
    }

    private boolean seEncuentra(NodoAbstracto<E> rsa, E dato) {
        if(rsa == null){
            return false;
        }

        int comparacion = comparar(dato, rsa.getDato());
        if(comparacion == 0){
            return true;
        }else if(comparacion > 0){
            return seEncuentra(rsa.getDerecho(), dato);
        }else{
            return seEncuentra(rsa.getIzquierdo(), dato);
        }
    }

    public int getPeso(){
        return getPeso(getRaiz());
    }

    private int getPeso(NodoAbstracto<E> rsa) {
        if(rsa == null)
            return 0;
        return 1 + getPeso(rsa.getIzquierdo()) + getPeso(rsa.getDerecho());
    }

    public int getAltura(){
        return getAltura(getRaiz());
    }

    private int getAltura(NodoAbstracto<E> rsa){
        if(rsa == null){
            return 0;
        }
        int alturaSI = getAltura(rsa.getIzquierdo());
        int alturaSD = getAltura(rsa.getDerecho());
        int alturaMayor = Math.max(alturaSI, alturaSD);
        return 1 + alturaMayor;
    }

    protected boolean esHoja(NodoAbstracto<E> nodo){
        return nodo.getIzquierdo() == null && nodo.getDerecho() == null;
    }

    public E getMayor(){
        if(getRaiz() == null)
            return null;
        return getMayor(getRaiz());
    }

    protected E getMayor(NodoAbstracto<E> actual){
        if(actual.getDerecho() != null){
            return getMayor(actual.getDerecho());
        }
        return actual.getDato();
    }

    public E getMenor(){
        if(getRaiz() == null)
            return null;
        return getMenor(getRaiz());
    }
    private E getMenor(NodoAbstracto<E> actual){
        if(actual.getIzquierdo() != null){
            return getMenor(actual.getIzquierdo());
        }
        return actual.getDato();
    }

    public List<E> getRecorridoInOrder(){
        List<E> list = new ArrayList<>();
        recorridoInOrder(getRaiz(), list);
        return list;
    }

    private void recorridoInOrder(NodoAbstracto<E> rsa, List<E> list) {

        if(rsa == null){
            return;
        }

        recorridoInOrder(rsa.getIzquierdo(), list);
        list.add(rsa.getDato());
        recorridoInOrder(rsa.getDerecho(), list);

    }

    public List<E> getRecorridoPreOrder(){
        List<E> list = new ArrayList<>();
        recorridoPreOrder(getRaiz(), list);
        return list;
    }

    private void recorridoPreOrder(NodoAbstracto<E> rsa, List<E> list) {

        if(rsa == null){
            return;
        }

        list.add(rsa.getDato());
        recorridoPreOrder(rsa.getIzquierdo(), list);
        recorridoPreOrder(rsa.getDerecho(), list);
    }

    public List<E> getRecorridoPostOrder(){
        List<E> list = new ArrayList<>();
        recorridoPostOrder(getRaiz(), list);
        return list;
    }

    private void recorridoPostOrder(NodoAbstracto<E> rsa, List<E> list) {

        if(rsa == null){
            return;
        }

        recorridoPostOrder(rsa.getIzquierdo(), list);
        recorridoPostOrder(rsa.getDerecho(), list);
        list.add(rsa.getDato());
    }

    public void borrar(){
        setRaiz(null);
    }
    public int comparar(E dato1, E dato2){
        if (comparator != null){
            return comparator.compare(dato1, dato2);
        }
        if(dato1 instanceof Comparable && dato2 instanceof Comparable){
            return ((Comparable<E>) dato1).compareTo(dato2);
            // Si > 0 => dato1 > dato2
            // Si = 0 => dato1 = dato2
            // Si < 0 => dato1 < dato2
        }else{
            throw new ClassCastException("No se puede comparar");
        }
    }

}
