package Data.Cache;

import Data.IDataBaseDocente;
import Model.Docente;

import java.util.ArrayList;
import java.util.List;

public class CacheDocenteImpl implements IDataBaseDocente {
    private List<Docente> docentes;

    public CacheDocenteImpl() {
        this.docentes = new ArrayList<>();
    }

    @Override
    public void save(Docente model) {
        docentes.add(model);
    }

    @Override
    public List<Docente> getAll() {
        return docentes;
    }

    @Override
    public Docente getByCode(String code) {
        for(Docente docente: docentes){
            if(docente.getCi().equals(code)){
                return docente;
            }
        }
        return  null;
    }
}
