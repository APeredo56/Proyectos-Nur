package com.example.hexagonal.domain.PaswordValidationChainRepository;

import com.example.hexagonal.domain.enums.ConfigurationValuePasswordLevelEnum;

public class MediunPasswordHandler extends Handler {

    @Override
    public void validate(String password, String passwordLevel) {
        if (!password.matches(".*[A-Z].*")){
            throw new IllegalArgumentException("La contraseña debe tener al menos una letra mayuscula");
        }


        if (passwordLevel.
                equals(ConfigurationValuePasswordLevelEnum.MEDIUM_PASSWORD.toString())){
            return;
        }


        if(nextHandler != null){
            nextHandler.validate(password, passwordLevel);
        }
    }
}
