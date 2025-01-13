package com.example.hexagonal.infrastructure.adapter.orm.user;

import com.example.hexagonal.application.dto.UserWithPharmacyDto;
import com.example.hexagonal.application.repositories.UserWithPharmacyRepository;
import com.example.hexagonal.infrastructure.adapter.orm.user.mapper.UserWithPharmacyDtoMapperIml;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserWithPharmacyRepositoryAdapter implements UserWithPharmacyRepository {
    private final UserRepositoryJpa userRepositoryJpa;
    private final UserWithPharmacyDtoMapperIml userWithPharmacyDtoMapperIml;

    @Override
    public UserWithPharmacyDto findById(long id) {
        UserEntity userEntity = userRepositoryJpa.findById(id).get();
        return userWithPharmacyDtoMapperIml.toDto(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPharmacyEntity().getId(),
                userEntity.getPharmacyEntity().getName()
        );
    }

}
