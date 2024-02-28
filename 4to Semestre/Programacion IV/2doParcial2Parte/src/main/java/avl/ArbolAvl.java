package avl;

import base.AbbAbstracto;
import base.NodoAbstracto;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Comparator;

public class ArbolAvl<E> extends AbbAbstracto<E> {

    private NodoAvl<E> raiz;

    public ArbolAvl(Comparator<E> comparator) {
        super(comparator);
    }

    public ArbolAvl() {
        super();
    }

    public void insertar(E dato){
        raiz = insertar(raiz, dato);
    }
    private NodoAvl<E> insertar(NodoAvl<E> rsa, E dato){
        if(rsa == null){
            rsa = new NodoAvl<>(dato);
        }else{
            int comparacion = comparar(dato, rsa.getDato());
            if(comparacion > 0){ // dato > rsa.getDato()

                //Insertamos a la derecha
                NodoAvl<E> recienInsertado = insertar(rsa.getDerecho(), dato);
                rsa.setDerecho(recienInsertado);

                //aqui termine de insertar a la derecha
                int nivelesSI = getNiveles(rsa.getIzquierdo());
                int nivelesSD = getNiveles(rsa.getDerecho());

                int factorEquilibrio = nivelesSD - nivelesSI;
                //Esta cargado a la derecha
                if(factorEquilibrio == 2){

                    nivelesSI = getNiveles(rsa.getDerecho().getIzquierdo());
                    nivelesSD = getNiveles(rsa.getDerecho().getDerecho());

                    factorEquilibrio = nivelesSD - nivelesSI;

                    if(factorEquilibrio < 0){
                        rsa = rotacionDobleIzquierda(rsa);
                    }else{
                        rsa = rotacionSimpleIzquierda(rsa);
                    }
                }

            }else if(comparacion < 0){ // dato < rsa.getDato()

                //Insertamos a la izquierda
                NodoAvl<E> recienInsertado = insertar(rsa.getIzquierdo(), dato);
                rsa.setIzquierdo(recienInsertado);

                //Aqui termine de insertar a la izquierda

                int nivelesSI = getNiveles(rsa.getIzquierdo());
                int nivelesSD = getNiveles(rsa.getDerecho());

                int factorEquilibrio = nivelesSD - nivelesSI;
                //Esta cargado a la derecha
                if(factorEquilibrio == -2){

                    nivelesSI = getNiveles(rsa.getIzquierdo().getIzquierdo());
                    nivelesSD = getNiveles(rsa.getIzquierdo().getDerecho());

                    factorEquilibrio = nivelesSD - nivelesSI;

                    if(factorEquilibrio > 0){
                        rsa = rotacionDobleDerecha(rsa);
                    }else{
                        rsa = rotacionSimpleDerecha(rsa);
                    }
                }

            }else{
                // dato ES IGUAL A rsa.getDato()
                // entonces no hago nada
            }
        }

        int nivelesIzquierda = getNiveles(rsa.getIzquierdo());
        int nivelesDerecha = getNiveles(rsa.getDerecho());
        int nivelesRsa = Math.max(nivelesIzquierda, nivelesDerecha) + 1;
        rsa.setCantidadNiveles(nivelesRsa);

        return rsa;
    }
    @Override
    public void eliminar(E dato){
        raiz = eliminar(raiz, dato);
    }

    private NodoAvl<E> eliminar(NodoAvl<E> rsa, E dato) {

        if(rsa == null){
            return rsa;
        }

        int comparacion = comparar(dato, rsa.getDato());
        if(comparacion == 0){
            //Aqui encontramos el elemento que queremos eliminar

            //Cuando es hoja
            if(esHoja(rsa)){
                rsa = null;
            } else if(rsa.getIzquierdo() == null){
                //Cuando le falta el hijo izquierdo
                rsa = rsa.getDerecho();
            }else if(rsa.getDerecho() == null){
                rsa = rsa.getIzquierdo();
            }else{
                //Cuando tiene ambos hijos
                E mayorSI = getMayor(rsa.getIzquierdo());
                rsa.setDato(mayorSI);

                NodoAvl<E> nuevaReferencia = eliminar(rsa.getIzquierdo(), mayorSI);
                rsa.setIzquierdo(nuevaReferencia);
            }



        }else if(comparacion > 0){
            NodoAvl<E> nuevaReferencia = eliminar(rsa.getDerecho(), dato);
            rsa.setDerecho(nuevaReferencia);

        }else{
            NodoAvl<E> nuevaReferencia = eliminar(rsa.getIzquierdo(), dato);
            rsa.setIzquierdo(nuevaReferencia);
        }

        if(rsa == null){
            return rsa;
        }

        int nivelesIzquierda = getNiveles(rsa.getIzquierdo());
        int nivelesDerecha = getNiveles(rsa.getDerecho());
        int nivelesRsa = Math.max(nivelesIzquierda, nivelesDerecha) + 1;
        rsa.setCantidadNiveles(nivelesRsa);

        int nivelesSI = getNiveles(rsa.getIzquierdo());
        int nivelesSD = getNiveles(rsa.getDerecho());

        int factorEquilibrio = nivelesSD - nivelesSI;

        if(factorEquilibrio == 2){

            nivelesSI = getNiveles(rsa.getDerecho().getIzquierdo());
            nivelesSD = getNiveles(rsa.getDerecho().getDerecho());

            factorEquilibrio = nivelesSD - nivelesSI;

            if(factorEquilibrio < 0){
                rsa = rotacionDobleIzquierda(rsa);
            }else{
                rsa = rotacionSimpleIzquierda(rsa);
            }
        }else if(factorEquilibrio == -2){
            nivelesSI = getNiveles(rsa.getIzquierdo().getIzquierdo());
            nivelesSD = getNiveles(rsa.getIzquierdo().getDerecho());

            factorEquilibrio = nivelesSD - nivelesSI;

            if(factorEquilibrio > 0){
                rsa = rotacionDobleDerecha(rsa);
            }else{
                rsa = rotacionSimpleDerecha(rsa);
            }
        }

        return rsa;

    }

    @Override
    protected void setRaiz(NodoAbstracto<E> raiz) {
        this.raiz = (NodoAvl<E>) raiz;
    }

    @Override
    public NodoAbstracto<E> getRaiz() {
        return this.raiz;
    }

    private NodoAvl<E> rotacionSimpleIzquierda(NodoAvl<E> rsa){
        NodoAvl<E> temp = rsa.getDerecho();
        rsa.setDerecho(temp.getIzquierdo());
        temp.setIzquierdo(rsa);
        return temp;
    }

    private NodoAvl<E> rotacionSimpleDerecha(NodoAvl<E> rsa){
        NodoAvl<E> temp = rsa.getIzquierdo();
        rsa.setIzquierdo(temp.getDerecho());
        temp.setDerecho(rsa);
        return temp;
    }

    private NodoAvl<E> rotacionDobleIzquierda(NodoAvl<E> rsa){
        NodoAvl<E> nuevoHijoDerecho = rotacionSimpleDerecha(rsa.getDerecho());
        rsa.setDerecho(nuevoHijoDerecho);

        NodoAvl<E> nuevoRsa = rotacionSimpleIzquierda(rsa);
        return nuevoRsa;
    }

    private NodoAvl<E> rotacionDobleDerecha(NodoAvl<E> rsa){
        NodoAvl<E> nuevoHijoIzquierdo = rotacionSimpleIzquierda(rsa.getIzquierdo());
        rsa.setIzquierdo(nuevoHijoIzquierdo);

        NodoAvl<E> nuevoRsa = rotacionSimpleDerecha(rsa);
        return nuevoRsa;
    }

    private int getNiveles(NodoAvl<E> nodo){
        if(nodo == null){
            return -1;
        }

        return  nodo.getCantidadNiveles();

    }

}
