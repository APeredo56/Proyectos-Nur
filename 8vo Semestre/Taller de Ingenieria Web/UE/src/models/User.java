package models;

public class User {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private UserType type;

    public User(int id, String name, String lastName, String email, String password, UserType type) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public boolean authenticate(String email, String password){
        if (this.password == null){
            return false;
        }
        return this.email.equals(email) && this.password.equals(password);
    }
}
