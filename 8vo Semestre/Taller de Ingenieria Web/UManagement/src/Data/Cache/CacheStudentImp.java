package Data.Cache;

import Data.IDataBaseStudent;
import Model.Enums.StudentType;
import Model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CacheStudentImp implements IDataBaseStudent {
    private List<Student> students;

    public CacheStudentImp() {
        this.students = new ArrayList<>();
    }

    @Override
    public void save(Student model) {
        students.add(model);
    }

    @Override
    public List<Student> getAll() {
        return students;
    }

    public List<Student> getByType(StudentType studentType) {
        return students.stream()
                .filter( studentObj -> studentObj.getType().equals(studentType))
                .collect(Collectors.toList());
    }

    @Override
    public Student getByCode(String code) {
        for(Student estudiante: students){
            if(estudiante.getCi().equals(code)){
                return estudiante;
            }
        }
        return  null;
    }
}
