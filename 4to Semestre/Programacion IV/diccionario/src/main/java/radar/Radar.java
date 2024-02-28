package radar;

import diccionario.Diccionario;
import diccionario.DiccionarioSecuencia;
import diccionario.DiccionarioTablaHash;

import java.util.ArrayList;
import java.util.List;

public class Radar {

    private Diccionario<Automovil, List<Automovil>> registro = new DiccionarioTablaHash<>(new ComparadorAutomovil());


    public void registrar(Automovil automovil){
        if (registro.obtener(automovil) == null){
            List<Automovil> velocidades = new ArrayList<>();
            velocidades.add(automovil);
            registro.insertar(automovil, velocidades);
        } else {
            registro.obtener(automovil).add(automovil);
        }
    }

    public EstadisticaRadar getEstadistica(Automovil automovil){
        List<Automovil> velocidades = registro.obtener(automovil);
        if (velocidades == null){
            return new EstadisticaRadar(0,0);
        }
        float promedio = 0;
        int veces;
        for (Automovil aux:velocidades) {
            promedio += aux.getVelocidad();
        }
        veces = velocidades.size();
        promedio = promedio / veces;
        EstadisticaRadar estadistica = new EstadisticaRadar(promedio, veces);
        return estadistica;
    }
}
