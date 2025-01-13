package Model;

public class Enrollment {
    String code;
    Subject subject;
    Student student;

    public Enrollment(String code, Subject subject, Student student) {
        this.code = code;
        this.subject = subject;
        this.student = student;
    }

    public Enrollment() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "code='" + code + '\'' +
                ", subject=" + subject +
                ", student=" + student +
                '}';
    }
}
