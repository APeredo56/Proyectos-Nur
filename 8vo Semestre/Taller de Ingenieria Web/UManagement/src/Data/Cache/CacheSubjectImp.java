package Data.Cache;


import Data.IDataBaseSubject;
import Model.Subject;

import java.util.ArrayList;
import java.util.List;

public class CacheSubjectImp implements IDataBaseSubject {
    private List<Subject> subjects;

    public CacheSubjectImp() {
        this.subjects = new ArrayList<>();
    }

    @Override
    public void save(Subject model) {
        subjects.add(model);
    }

    @Override
    public List<Subject> getAll() {
        return subjects;
    }

    @Override
    public Subject getByCode(String code) {
        for(Subject subject: subjects){
            if(subject.getCode().equals(code)){
                return subject;
            }
        }
        return  null;
    }
}
