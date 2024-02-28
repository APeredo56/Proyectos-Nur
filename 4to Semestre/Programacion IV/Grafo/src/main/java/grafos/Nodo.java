package grafos;

import diccionario.Comparador;
import diccionario.Diccionario;
import diccionario.DiccionarioTablaHash;

import java.util.List;

class Nodo<K, V> {

    private K key;
    private V value;

    private Diccionario<K, Nodo<K, V>> aristas;

    public Nodo(K key, V value, Comparador<K> comparador) {
        this.key = key;
        this.value = value;
        this.aristas = new DiccionarioTablaHash<>(comparador);
    }

    public void insertarArista(Nodo<K, V> vertice) {
        this.aristas.insertar(vertice.getKey(), vertice);
    }

    public V eliminarArista(K idVertice) {
        if (aristas.contieneLlave(idVertice)) {
            return aristas.eliminar(idVertice).getValue();
        }
        return null;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public String toString() {
        StringBuilder str = new StringBuilder(key.toString());
        str.append(",");
        str.append(value.toString());

        List<K> idAristas = aristas.getLlaves();
        str.append(" => [");
        String separator = "";
        for (K id : idAristas) {
            str.append(separator).append(id);
            separator = ", ";
        }
        str.append("]");

        return str.toString();
    }
}
