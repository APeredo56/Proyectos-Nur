package Data.Cache;


import Data.IDataBaseGrade;
import Model.Grade;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CacheGradeImp implements IDataBaseGrade {
    private List<Grade> grades;

    public CacheGradeImp() {
        this.grades = new ArrayList<>();
    }

    @Override
    public void save(Grade model) {
        grades.add(model);
    }

    @Override
    public List<Grade> getAll() {
        return grades;
    }

    @Override
    public Grade getByCode(String code) {
        for (Grade grade : grades) {
            if (grade.getCode().equals(code)) {
                return grade;
            }
        }
        return null;
    }

    @Override
    public List<Grade> getByStudentAndSubject(String studentCi, String subjectCode) {
        return grades.stream()
                .filter(grade -> grade.getStudent().getCi().equals(studentCi) &&
                        grade.getSubject().getCode().equals(subjectCode))
                .collect(Collectors.toList());
    }
}
