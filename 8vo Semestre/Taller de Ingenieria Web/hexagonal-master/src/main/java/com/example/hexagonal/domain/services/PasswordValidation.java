package com.example.hexagonal.domain.services;

import com.example.hexagonal.domain.PaswordValidationChainRepository.Handler;
import com.example.hexagonal.domain.PaswordValidationChainRepository.MediunPasswordHandler;
import com.example.hexagonal.domain.PaswordValidationChainRepository.WeakPasswordHandler;

public class PasswordValidation {

    public PasswordValidation(String password, String passwordLevel) {

        Handler weakPasswordHandler = new WeakPasswordHandler();
        Handler mediumPasswordHandler = new MediunPasswordHandler();

        weakPasswordHandler.setNextHandler(mediumPasswordHandler);

        weakPasswordHandler.validate(password, passwordLevel);
    }
}
