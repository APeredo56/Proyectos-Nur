package Data.Mysql;

import Data.IDataBaseEstudiante;
import Model.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class MysqlEstudianteImp implements IDataBaseEstudiante {

    @Override
    public void save(Estudiante model) {

    }

    @Override
    public List<Estudiante> getAll() {
        return Collections.emptyList();
    }

    @Override
    public Estudiante getByCode(String code) {
        return null;
    }

    @Override
    public List<Estudiante> getByTipo(String tipoEstduiante) {
        return null;
    }
}
