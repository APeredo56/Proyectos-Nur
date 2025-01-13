package com.example.hexagonal.infrastructure.adapter.orm.user.mapper;

import com.example.hexagonal.application.dto.UserDto;
import com.example.hexagonal.application.mapper.UserDtoMapper;
import com.example.hexagonal.domain.model.user.User;
import com.example.hexagonal.infrastructure.adapter.orm.user.UserEntity;

public class UserDtoMapperImp implements UserDtoMapper<UserEntity> {

    @Override
    public UserDto toDto(UserEntity userEntity) {
        return new UserDto(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail()
        );
    }


}
