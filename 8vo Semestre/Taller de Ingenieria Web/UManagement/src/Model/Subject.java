package Model;

import Model.Enums.GradeType;

public class Subject {
    private String code;
    private String name;
    private Teacher teacher;
    private GradeType gradeType;

    public Subject(String code, String name, GradeType gradeType, Teacher teacher) {
        this.code = code;
        this.name = name;
        this.gradeType = gradeType;
        this.teacher = teacher;
    }

    public Subject() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public GradeType getGradeType() {
        return gradeType;
    }

    public void setGradeType(GradeType gradeType) {
        this.gradeType = gradeType;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", teacher=" + teacher +
                ", gradeType=" + gradeType +
                '}';
    }
}
