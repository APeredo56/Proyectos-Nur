package abb;

import base.AbbAbstracto;
import base.NodoAbstracto;

import java.awt.*;
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
        List<E> lista = new ArrayList<>();
        if (!seEncuentra(desde) || !seEncuentra(hasta)){
            return lista;
        }
        if (desde.equals(hasta)){
            lista.add(desde);
            return lista;
        }
        Nodo<E> inicio = prueba(desde);
        Nodo<E> fin = prueba(hasta);
        Nodo<E> actual = inicio;
        ArrayList<Boolean> lista2 = new ArrayList<>();

        return null;
    }



    public Nodo<E> prueba(E dato){
        ArrayList<Nodo<E>> lista = new ArrayList<>();
        obtenerNodo(raiz, lista, dato);
        return lista.get(0);
    }



    public void obtenerNodo(Nodo<E> nodo, List<Nodo<E>> list, E dato){
        if(nodo == null){
            return;
        }
        if (nodo.getDato().equals(dato)){
            list.add(nodo);
        }
        obtenerNodo(nodo.getIzquierdo(), list, dato);
        obtenerNodo(nodo.getDerecho(), list, dato);
    }

    public void obtenerRecorrido(Nodo<E> nodo, List<Nodo<E>> list, E dato, List<Boolean> lista){
        if(nodo == null){
            return;
        }
        if (nodo.getDato().equals(dato)){
            list.add(nodo);
        }
        obtenerNodo(nodo.getIzquierdo(), list, dato);
        obtenerNodo(nodo.getDerecho(), list, dato);
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



    @Override
    protected void setRaiz(NodoAbstracto<E> raiz) {
        this.raiz = (Nodo<E>) raiz;
    }

    @Override
    public NodoAbstracto<E> getRaiz() {
        return raiz;
    }



}
