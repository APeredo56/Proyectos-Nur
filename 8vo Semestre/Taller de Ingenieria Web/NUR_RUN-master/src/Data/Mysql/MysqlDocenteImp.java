package Data.Mysql;

import Data.IDataBaseDocente;
import Data.IDataBaseEstudiante;
import Model.Docente;
import Model.Estudiante;

import java.util.Collections;
import java.util.List;

public class MysqlDocenteImp implements IDataBaseDocente {


    @Override
    public void save(Docente model) {

    }

    @Override
    public List<Docente> getAll() {
        return Collections.emptyList();
    }

    @Override
    public Docente getByCode(String code) {
        return null;
    }
}
