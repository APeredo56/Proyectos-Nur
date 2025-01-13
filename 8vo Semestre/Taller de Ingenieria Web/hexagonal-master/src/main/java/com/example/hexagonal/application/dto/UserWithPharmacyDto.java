package com.example.hexagonal.application.dto;

public class UserWithPharmacyDto {
    private long id;
    private String name;
    private String email;
    private PharmacyDto pharmacy;

    public UserWithPharmacyDto(long id, String name, String email, PharmacyDto pharmacyDto) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pharmacy = pharmacyDto;
    }

    public UserWithPharmacyDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PharmacyDto getPharmacyDto() {
        return pharmacy;
    }

    public void setPharmacyDto(PharmacyDto pharmacyDto) {
        this.pharmacy = pharmacyDto;
    }
}
