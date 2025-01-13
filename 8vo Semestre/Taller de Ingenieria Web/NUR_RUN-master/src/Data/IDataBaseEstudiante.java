package Data;

import Model.Estudiante;

import java.util.List;


public interface IDataBaseEstudiante extends IDataBase<Estudiante>{
    public List<Estudiante> getByTipo(String tipoEstduiante);
}
