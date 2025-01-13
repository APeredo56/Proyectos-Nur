package com.example.hexagonal.domain.model.user.valueObject;

public class UserEmailValue {

    private final String value;

    public UserEmailValue(String value) {
        if (!validateEmail(value)) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.value = value;
    }

    private boolean validateEmail(String email) {
        return email.matches("^(.+)@(.+)$");
    }


    public String getValue() {
        return this.value;
    }


}
