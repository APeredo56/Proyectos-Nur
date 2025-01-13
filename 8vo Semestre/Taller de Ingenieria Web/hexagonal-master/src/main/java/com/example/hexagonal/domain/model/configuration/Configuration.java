package com.example.hexagonal.domain.model.configuration;

public class Configuration {

    private String idString;
    private String data;

    public Configuration() {
    }

    public Configuration(String idString, String data) {
        this.idString = idString;
        this.data = data;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
