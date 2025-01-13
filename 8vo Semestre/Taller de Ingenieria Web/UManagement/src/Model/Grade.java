package Model;

public class Grade {
    private String code;
    private Student student;
    private Subject subject;
    private int grade;
    private double weight;

    public Grade(String code, Student student, Subject subject, int grade, double weight) {
        this.code = code;
        this.student = student;
        this.subject = subject;
        this.grade = grade;
        this.weight = weight;
    }

    public Grade() {
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "code='" + code + '\'' +
                ", grade=" + grade +
                ", weight=" + weight *100 + "%" +
                '}';
    }
}
