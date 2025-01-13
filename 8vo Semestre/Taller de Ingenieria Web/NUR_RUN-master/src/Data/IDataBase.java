package Data;


import Model.Estudiante;

import java.sql.SQLException;
import java.util.List;

public interface IDataBase<T> {
    public void save(T model) ;
    public List<T> getAll();
    public T getByCode(String code);
}
