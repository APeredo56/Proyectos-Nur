package Data.Mysql;

import Data.IDataBaseEstudiante;
import Data.IDataBaseMateria;
import Model.Estudiante;
import Model.Materia;

import java.util.Collections;
import java.util.List;

public class MysqlMateriaImp implements IDataBaseMateria {

    @Override
    public void save(Materia model) {

    }

    @Override
    public List<Materia> getAll() {
        return Collections.emptyList();
    }

    @Override
    public Materia getByCode(String code) {
        return null;
    }
}
