package radar;

import diccionario.Comparador;
import radar.Automovil;

public class ComparadorAutomovil implements Comparador<Automovil> {
    @Override
    public boolean esIgual(Automovil key1, Automovil key2) {
        return key1.toString().equals(key2.toString());
    }

    @Override
    public int getHashCode(Automovil key) {
        return key.getMatricula().hashCode();
    }
}


