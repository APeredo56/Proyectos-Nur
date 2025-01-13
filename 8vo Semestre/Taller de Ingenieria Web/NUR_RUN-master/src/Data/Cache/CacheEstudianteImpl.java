package Data.Cache;

import Data.IDataBaseEstudiante;
import Model.Estudiante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CacheEstudianteImpl implements IDataBaseEstudiante {
    private List<Estudiante> estudiantes;

    public CacheEstudianteImpl() {
        this.estudiantes = new ArrayList<>();
    }

    @Override
    public void save(Estudiante model) {
        estudiantes.add(model);
    }

    @Override
    public List<Estudiante> getAll() {
        return Collections.emptyList();
    }

    @Override
    public List<Estudiante> getByTipo(String tipoEstduiante) {
        return estudiantes.stream()
                .filter( estu -> estu.getTipo().equals(tipoEstduiante))
                .collect(Collectors.toList());
    }

    @Override
    public Estudiante getByCode(String code) {
        for(Estudiante estudiante: estudiantes){
            if(estudiante.getCi().equals(code)){
                return estudiante;
            }
        }
        return  null;
    }
}
