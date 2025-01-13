package Data.Cache;

import Data.IDataBaseStudentGraduateType;
import Model.StudentGraduateType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CacheStudentGraduateTypeImp implements IDataBaseStudentGraduateType {
    private List<StudentGraduateType> studentGraduateTypes;

    public CacheStudentGraduateTypeImp() {
        this.studentGraduateTypes = new ArrayList<>();
    }

    @Override
    public void save(StudentGraduateType model) {
        studentGraduateTypes.add(model);
    }

    @Override
    public List<StudentGraduateType> getAll() {
        return studentGraduateTypes;
    }

    @Override
    public StudentGraduateType getByCode(String code) {
        for(StudentGraduateType enrollment: studentGraduateTypes){
            if(enrollment.getCode().equals(code)){
                return enrollment;
            }
        }
        return  null;
    }

    @Override
    public StudentGraduateType getByStudent(String studentCi) {
        for(StudentGraduateType studentGraduateType: studentGraduateTypes){
            if(studentGraduateType.getStudent().getCi().equals(studentCi)){
                return studentGraduateType;
            }
        }
        return  null;
    }
}
