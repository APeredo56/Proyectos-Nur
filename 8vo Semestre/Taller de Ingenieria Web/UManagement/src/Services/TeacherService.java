package Services;

import Data.IDataBaseTeacher;
import Data.DataManager;
import Model.Teacher;

import java.util.List;

public class TeacherService extends BaseService implements IDataBaseTeacher {
    public TeacherService(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public List<Teacher> getAll() {
        return dataManager.getDataTeacher().getAll();
    }

    @Override
    public Teacher getByCode(String code) {
        return super.dataManager.getDataTeacher().getByCode(code);
    }

    @Override
    public void save(Teacher teacher) {
        dataManager.getDataTeacher().save(teacher);
    }
}
