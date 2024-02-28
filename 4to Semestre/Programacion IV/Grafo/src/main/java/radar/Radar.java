package radar;

import diccionario.Comparador;
import diccionario.Diccionario;
import diccionario.DiccionarioTablaHash;

import java.util.ArrayList;
import java.util.List;

public class Radar {

    private Diccionario<Automovil, List<Automovil>> registro;

    public Radar() {
        registro = new DiccionarioTablaHash<>(new Comparador<Automovil>() {
            @Override
            public boolean esIgual(Automovil key1, Automovil key2) {
                return key1.getMatricula().equals(key2.getMatricula());
            }

            @Override
            public int getHashCode(Automovil key) {
                return key.getMatricula().hashCode();
            }
        });
    }

    public void registrar(Automovil automovil) {
        if (!registro.contieneLlave(automovil)) {
            registro.insertar(automovil, new ArrayList<>());
        }
        registro.obtener(automovil).add(automovil);
    }

    public EstadisticaRadar obtenerEstadistica(Automovil automovil) {

        if(!registro.contieneLlave(automovil)){
            return new EstadisticaRadar(0,0);
        }
        List<Automovil> lista = registro.obtener(automovil);
        float velocidadPromedio = 0;
        int veces = 0;
        veces = lista.size();
        for (Automovil auto : lista) {

                velocidadPromedio += auto.getVelocidad();


        }
        velocidadPromedio = velocidadPromedio / veces;


        return new EstadisticaRadar(velocidadPromedio, veces);
    }
}
