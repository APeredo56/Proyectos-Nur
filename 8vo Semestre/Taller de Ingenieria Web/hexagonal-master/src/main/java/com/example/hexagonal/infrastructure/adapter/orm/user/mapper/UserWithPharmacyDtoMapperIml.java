package com.example.hexagonal.infrastructure.adapter.orm.user.mapper;

import com.example.hexagonal.application.dto.PharmacyDto;
import com.example.hexagonal.application.dto.UserWithPharmacyDto;
import com.example.hexagonal.application.mapper.UserWithPharmacyDtoMapper;
import org.springframework.stereotype.Service;

@Service
public class UserWithPharmacyDtoMapperIml implements UserWithPharmacyDtoMapper {

    @Override
    public UserWithPharmacyDto toDto(long userId, String userName,String userEmail, long pharmacyId, String pharmacyName) {

        PharmacyDto pharmacyDto = new PharmacyDto(pharmacyId, pharmacyName);
        return new UserWithPharmacyDto(userId, userName, userEmail, pharmacyDto);
    }
}
