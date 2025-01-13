package com.example.hexagonal.infrastructure.adapter.orm.user;

import com.example.hexagonal.application.dto.UserDto;
import com.example.hexagonal.application.dto.UserWithPharmacyDto;
import com.example.hexagonal.application.repositories.UserRepository;
import com.example.hexagonal.domain.model.user.User;
import com.example.hexagonal.infrastructure.adapter.orm.pharmacy.PhamacyRepositoryJpa;
import com.example.hexagonal.infrastructure.adapter.orm.pharmacy.PharmacyEntity;
import com.example.hexagonal.infrastructure.adapter.orm.user.mapper.UserDtoMapperImp;
import com.example.hexagonal.infrastructure.adapter.orm.user.mapper.UserMapper;
import com.example.hexagonal.infrastructure.adapter.orm.user.mapper.UserWithPharmacyDtoMapperIml;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final UserRepositoryJpa userRepositoryJpa;
    private final PhamacyRepositoryJpa pharmacyRepositoryJpa;
    private final UserMapper userMapper;


    @Override
    public User findById(long id) {
        UserEntity userEntity = userRepositoryJpa.findById(id).get();

        return userMapper.toDomain(userEntity);
    }

    @Override
    public User save(User user) {
        PharmacyEntity pharmacyEntity = pharmacyRepositoryJpa.findById(
                user.getPharmacy_id()
        ).orElseThrow(() -> new RuntimeException("Pharmacy not exist"));

        UserEntity userEntity = userMapper.toEntity(user);

        userEntity.setPharmacyEntity(pharmacyEntity);

        userEntity = userRepositoryJpa.save(userEntity);

        return userMapper.toDomain(userEntity);
    }
}
