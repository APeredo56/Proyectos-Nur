package com.example.hexagonal.domain.model.user.valueObject;

public class UserPasswordValue {

    private final String value;

    public UserPasswordValue(String value) {
        validatePassword(value);
        this.value = value;
    }

    public void validatePassword(String value) {
        if (!minLength(value)) {
            throw new IllegalArgumentException("Password must have at least 8 characters");
        }
        if (!hasUppercase(value)) {
            throw new IllegalArgumentException("Password must have at least one uppercase letter");
        }
        if (!hasLowercase(value)) {
            throw new IllegalArgumentException("Password must have at least one lowercase letter");
        }
    }

    private boolean minLength(String password) {
        return password.length() >= 8;
    }

    private boolean hasUppercase(String password) {
        return !password.equals(password.toLowerCase());
    }

    private boolean hasLowercase(String password) {
        return !password.equals(password.toUpperCase());
    }


    public String getValue() {
        return value;
    }
}
