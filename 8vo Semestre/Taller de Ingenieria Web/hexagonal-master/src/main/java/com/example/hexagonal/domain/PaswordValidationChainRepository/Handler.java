package com.example.hexagonal.domain.PaswordValidationChainRepository;

public abstract  class Handler {

    protected Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void validate(String password, String passwordLevel);

}
