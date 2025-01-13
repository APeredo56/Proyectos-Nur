package Model;

import Model.Enums.GraduateType;

public class StudentGraduateType {
    private String code;
    private Student student;
    private GraduateType graduateType;

    public StudentGraduateType(String code, Student student, GraduateType graduateType) {
        this.code = code;
        this.student = student;
        this.graduateType = graduateType;
    }

    public StudentGraduateType() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public GraduateType getGraduateType() {
        return graduateType;
    }

    public void setGraduateType(GraduateType graduateType) {
        this.graduateType = graduateType;
    }

    @Override
    public String toString() {
        return "StudentGraduateType{" +
                "code='" + code + '\'' +
                ", student=" + student +
                ", graduateType=" + graduateType +
                '}';
    }
}
