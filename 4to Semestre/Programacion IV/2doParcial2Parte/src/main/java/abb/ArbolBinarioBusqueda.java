package abb;

import base.AbbAbstracto;
import base.NodoAbstracto;

import java.util.ArrayList;
import java.util.Comparator;
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

    public List<E> getRecorridoDesdeHasta(E desde, E hasta){
        return null;
    }

    @Override
    protected void setRaiz(NodoAbstracto<E> raiz) {
        this.raiz = (Nodo<E>) raiz;
    }

    @Override
    public NodoAbstracto<E> getRaiz() {
        return raiz;
    }



}
