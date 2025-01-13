package Data.Cache;

import Data.IDataBaseEnrollment;
import Model.Enrollment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CacheEnrollmentImp implements IDataBaseEnrollment {
    private List<Enrollment> enrollments;

    public CacheEnrollmentImp() {
        this.enrollments = new ArrayList<>();
    }

    @Override
    public void save(Enrollment model) {
        enrollments.add(model);
    }

    @Override
    public List<Enrollment> getAll() {
        return enrollments;
    }

    @Override
    public Enrollment getByCode(String code) {
        for(Enrollment enrollment: enrollments){
            if(enrollment.getCode().equals(code)){
                return enrollment;
            }
        }
        return  null;
    }


    @Override
    public List<Enrollment> getByStudent(String studentCi) {
        return enrollments.stream()
                .filter( enrollment -> enrollment.getStudent().getCi().equals(studentCi))
                .collect(Collectors.toList());
    }

    @Override
    public List<Enrollment> getBySubject(String subjectCode) {
        return enrollments.stream()
                .filter( enrollment -> enrollment.getSubject().getCode().equals(subjectCode))
                .collect(Collectors.toList());
    }
}
