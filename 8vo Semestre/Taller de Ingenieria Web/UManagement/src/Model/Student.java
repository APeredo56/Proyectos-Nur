package Model;

import Model.Enums.StudentType;

public class Student {

    private String ci;
    private String name;
    private String lastName;
    private String phone;
    private StudentType type;

    public Student(String ci, String name, String lastName, String phone, StudentType type) {
        this.ci = ci;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.type = type;
    }

    public Student() {
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public StudentType getType() {
        return type;
    }

    public void setType(StudentType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "ci='" + ci + '\'' +
                ", nombre='" + name + '\'' +
                ", apellido='" + lastName + '\'' +
                ", telefono='" + phone + '\'' +
                ", tipo='" + type + '\'' +
                '}';
    }
}
