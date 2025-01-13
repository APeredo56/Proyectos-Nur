package com.example.hexagonal.domain.PaswordValidationChainRepository;

import com.example.hexagonal.domain.enums.ConfigurationValuePasswordLevelEnum;

public class WeakPasswordHandler extends Handler{
    @Override
    public void validate(String password, String passwordLevel) {
        if(password.length() < 8){
            throw new IllegalArgumentException("La contraseña no deve tener menos de 8 caracteres");
        }

        if (passwordLevel.
                equals(ConfigurationValuePasswordLevelEnum.MEAK_PASSWORD.toString())){
            return;
        }


        if(nextHandler != null){
            nextHandler.validate(password, passwordLevel);
        }
    }
}
