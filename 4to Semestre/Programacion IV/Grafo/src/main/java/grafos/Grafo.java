package grafos;

import diccionario.Comparador;
import diccionario.Diccionario;
import diccionario.DiccionarioTablaHash;

import java.util.List;

public class Grafo<K, V> {

    private Comparador<K> comparador;
    private Diccionario<K, Nodo<K,V>> vertices;

    public Grafo(Comparador<K> comparador){
        this.comparador = comparador;
        this.vertices = new DiccionarioTablaHash<>(comparador);
    }

    public void insertarVertice(K key, V value){
        validarLlave(key, "key");
        if(vertices.contieneLlave(key)){
            throw new IllegalArgumentException("Ya existe un vertice con con esa llave");
        }
        Nodo<K,V> nodo = new Nodo<>(key, value, comparador);
        vertices.insertar(key, nodo);
    }

    public void insertarArista(K keyOrigen, K keyDestino){
        validarLlavesExistentes(keyOrigen, keyDestino);

        Nodo<K,V> verticeOrigen = vertices.obtener(keyOrigen);
        Nodo<K,V> verticeDestino = vertices.obtener(keyDestino);

        verticeOrigen.insertarArista(verticeDestino);
    }

    public void eliminarArista(K keyOrigen, K keyDestino){
        validarLlavesExistentes(keyOrigen, keyDestino);

        Nodo<K,V> verticeOrigen = vertices.obtener(keyOrigen);
        verticeOrigen.eliminarArista(keyDestino);
    }

    private void validarLlavesExistentes(K keyOrigen, K keyDestino) {
        validarLlave(keyOrigen, "keyOrigen");
        validarLlave(keyDestino, "keyDestino");

        if (!vertices.contieneLlave(keyDestino)) {
            throw new IllegalArgumentException("No existe un vertice con KeyOrigen");
        }
        if (!vertices.contieneLlave(keyDestino)) {
            throw new IllegalArgumentException("No existe un vertice con keyDestino");
        }
    }

    private void validarLlave(K key, String keyName){
        if(key == null){
            throw new IllegalArgumentException(keyName + " no puede ser nula");
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder();

        List<Nodo<K, V>> nodos = vertices.getValores();
        String separator = "";
        for (Nodo<K, V> nodo : nodos) {
            str.append(separator).append(nodo.toString());
            separator = "\n";
        }

        return str.toString();
    }
}
