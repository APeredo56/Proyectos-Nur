package com.example.hexagonal.application.repositories;

import com.example.hexagonal.application.dto.UserWithPharmacyDto;

public interface UserWithPharmacyRepository {

    public UserWithPharmacyDto findById(long id);
    //public UserWithPharmacyDto save(UserWithPharmacyDto user);
}
