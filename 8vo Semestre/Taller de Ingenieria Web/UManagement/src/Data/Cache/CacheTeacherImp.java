package Data.Cache;

import Data.IDataBaseTeacher;
import Model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class CacheTeacherImp implements IDataBaseTeacher {
    private List<Teacher> docentes;

    public CacheTeacherImp() {
        this.docentes = new ArrayList<>();
    }

    @Override
    public void save(Teacher model) {
        docentes.add(model);
    }

    @Override
    public List<Teacher> getAll() {
        return docentes;
    }

    @Override
    public Teacher getByCode(String code) {
        for(Teacher docente: docentes){
            if(docente.getCi().equals(code)){
                return docente;
            }
        }
        return  null;
    }
}
