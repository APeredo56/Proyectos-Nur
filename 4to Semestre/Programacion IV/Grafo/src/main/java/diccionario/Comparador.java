package diccionario;

public interface Comparador<K> {

    public boolean esIgual(K key1, K key2);

    int getHashCode(K key);
}
