package Services;

import Data.IDataBaseStudentGraduateType;
import Model.StudentGraduateType;
import Data.DataManager;

import java.util.List;

public class StudentGraduateTypeService extends BaseService implements IDataBaseStudentGraduateType {


    public StudentGraduateTypeService(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public StudentGraduateType getByCode(String code) {
        return super.dataManager.getDataStudentGraduateType().getByCode(code);
    }

    @Override
    public void save(StudentGraduateType enrollment) {
        dataManager.getDataStudentGraduateType().save(enrollment);
    }

    @Override
    public List<StudentGraduateType> getAll() {
        return super.dataManager.getDataStudentGraduateType().getAll();
    }

    @Override
    public StudentGraduateType getByStudent(String studentCi) {
        return  super.dataManager.getDataStudentGraduateType().getByStudent(studentCi);
    }
}
