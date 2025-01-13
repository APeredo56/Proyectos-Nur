package Data;

import Data.Cache.CacheDocenteImpl;
import Data.Cache.CacheEstudianteImpl;
import Data.Cache.CacheMateriaImpl;
import Data.Mysql.MysqlEstudianteImp;

public class ManagerData {

    private IDataBaseMateria dataMateria;
    private  IDataBaseEstudiante dataEstudiante;
    private  IDataBaseDocente dataDocente;

    public ManagerData(String type) {
        if(type.equals("cache")){
            dataMateria = new CacheMateriaImpl();
            dataEstudiante = new CacheEstudianteImpl();
            dataDocente = new CacheDocenteImpl();
        }else if(type.equals("mysql")) {
            dataEstudiante = new MysqlEstudianteImp();
        }
    }


    public IDataBaseMateria getDataMateria() {
        return dataMateria;
    }

    public IDataBaseEstudiante getDataEstudiante() {
        return dataEstudiante;
    }

    public IDataBaseDocente getDataDocente() {
        return dataDocente;
    }
}
