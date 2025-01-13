package Data.Cache;


import Data.IDataBaseMateria;
import Model.Materia;

import java.util.ArrayList;
import java.util.List;

public class CacheMateriaImpl implements IDataBaseMateria {
    private List<Materia> materias;

    public CacheMateriaImpl() {
        this.materias = new ArrayList<>();
    }

    @Override
    public void save(Materia model) {
        materias.add(model);
    }

    @Override
    public List<Materia> getAll() {
        return materias;
    }

    @Override
    public Materia getByCode(String code) {
        for(Materia docente: materias){
            if(docente.getCod().equals(code)){
                return docente;
            }
        }
        return  null;
    }
}
