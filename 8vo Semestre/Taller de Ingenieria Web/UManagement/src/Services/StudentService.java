package Services;

import Data.IDataBaseStudent;
import Data.DataManager;
import Model.Enums.StudentType;
import Model.Student;

import java.util.List;

public class StudentService extends BaseService implements IDataBaseStudent {


    public StudentService(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Student getByCode(String code) {
        return super.dataManager.getDataStudent().getByCode(code);
    }

    @Override
    public void save(Student student) {
        dataManager.getDataStudent().save(student);
    }

    @Override
    public List<Student> getAll() {
        return super.dataManager.getDataStudent().getAll();
    }

    @Override
    public List<Student> getByType(StudentType studentType) {
        return super.dataManager.getDataStudent().getByType(studentType);
    }
}
