package Model;

public class Teacher {
    private String ci;
    private String name;
    private String lastName;
    private String phone;

    public Teacher(String ci, String name, String lastName, String phone) {
        this.ci = ci;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Teacher() {
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

    @Override
    public String toString() {
        return "Teacher{" +
                "ci='" + ci + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getFullName(){
        return name + " " + lastName;
    }
}
