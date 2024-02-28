package diccionario;

import java.util.ArrayList;
import java.util.List;

public class DiccionarioTablaHash<K, V> extends Diccionario<K, V> {

    private DiccionarioSecuencia<K, V>[] elementos;
    private float factorCarga;
    private int limiteElementos;

    public DiccionarioTablaHash(Comparador<K> comparador) {
        super(comparador);
        init();
    }

    public DiccionarioTablaHash() {
        super();
        init();
    }

    @Override
    public void insertar(K key, V value) {
        int indice = getIndice(key);
        if (elementos[indice] == null) {
            elementos[indice] = new DiccionarioSecuencia<>(comparador);
        }
        int antiguaCantidadDeElementos = elementos[indice].getCantidadElementos();
        elementos[indice].insertar(key, value);
        int actualCantidadDeElementos = elementos[indice].getCantidadElementos();
        if (antiguaCantidadDeElementos + 1 == actualCantidadDeElementos) {
            cantidadElementos++;
        }

        if (cantidadElementos > limiteElementos) {
            //rehash();
            int newSize = getNewSize();
            DiccionarioSecuencia<K,V>[] nuevo = new DiccionarioSecuencia[newSize];

            //redistribuir los elementos en base al nuevo tamaño
            limiteElementos = (int) (newSize * factorCarga);
            for (DiccionarioSecuencia<K, V> diccionarioSecuencia : elementos) {
                if (diccionarioSecuencia != null) {
                    for (K k : diccionarioSecuencia.getLlaves()) {
                        V v = diccionarioSecuencia.obtener(k);
                        int indiceNuevo = getIndice(k);
                        if (nuevo[indiceNuevo] == null) {
                            nuevo[indiceNuevo] = new DiccionarioSecuencia<>(comparador);
                        }
                        nuevo[indiceNuevo].insertar(k, v);
                    }
                }
            }

            elementos = nuevo;

        }
    }

    private int getNewSize() {
        return elementos.length * 2 + 1;
    }

    private int getIndice(K key) {
        return getIndice(key, elementos.length);
    }

    private int getIndice(K key, int n) {

        return (this.comparador.getHashCode(key) & 0x7FFFFFFF) % n;
    }

    @Override
    public V obtener(K key) {

        int indice = getIndice(key);
        if (elementos[indice] == null) {

            return null;
        }

        return elementos[indice].obtener(key);
    }

    @Override
    public V eliminar(K key) {
        int indice = getIndice(key);
        if (elementos[indice] == null) {
            return null;
        }
        V valor = elementos[indice].eliminar(key);
        if (valor != null) {
            cantidadElementos--;
        }
        return valor;
    }

    @Override
    public boolean contieneLlave(K key) {
        int indice = getIndice(key);
        if (elementos[indice] == null) {
            return false;
        }
        return elementos[indice].contieneLlave(key);
    }

    @Override
    public List<K> getLlaves() {
        List<K> llaves = new ArrayList<>();
        for (DiccionarioSecuencia<K, V> diccionarioSecuencia : elementos) {
            if (diccionarioSecuencia != null) {
                llaves.addAll(diccionarioSecuencia.getLlaves());
            }
        }

        return llaves;
    }

    @Override
    public List<V> getValores() {
        List<V> valores = new ArrayList<>();
        for (DiccionarioSecuencia<K, V> diccionarioSecuencia : elementos) {
            if (diccionarioSecuencia != null) {
                valores.addAll(diccionarioSecuencia.getValores());
            }
        }

        return valores;
    }

    private void init() {
        elementos = new DiccionarioSecuencia[11];
        factorCarga = 0.75f;
        limiteElementos = (int) (elementos.length * factorCarga);
    }


}
