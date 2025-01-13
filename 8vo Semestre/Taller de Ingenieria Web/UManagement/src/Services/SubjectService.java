package Services;

import Data.IDataBaseSubject;
import Data.DataManager;
import Model.Subject;

import java.util.List;

public class SubjectService extends BaseService implements IDataBaseSubject {
    public SubjectService(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public List<Subject> getAll() {
        return dataManager.getDataSubject().getAll();
    }

    @Override
    public void save(Subject subject) {
        dataManager.getDataSubject().save(subject);
    }

    @Override
    public Subject getByCode(String code) {
        return dataManager.getDataSubject().getByCode(code);
    }
}
