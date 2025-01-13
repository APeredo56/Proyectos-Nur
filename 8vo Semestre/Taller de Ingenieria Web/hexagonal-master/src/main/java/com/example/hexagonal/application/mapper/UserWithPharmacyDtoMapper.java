package com.example.hexagonal.application.mapper;

import com.example.hexagonal.application.dto.UserWithPharmacyDto;
import com.example.hexagonal.domain.model.pharmacy.Pharmacy;
import com.example.hexagonal.domain.model.user.User;

public interface UserWithPharmacyDtoMapper {

    UserWithPharmacyDto toDto(long userId, String userName, String userEmail, long pharmacyId, String pharmacyName);
}
