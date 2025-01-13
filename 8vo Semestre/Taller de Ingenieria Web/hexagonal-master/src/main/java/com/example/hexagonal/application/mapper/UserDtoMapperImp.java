package com.example.hexagonal.application.mapper;

import com.example.hexagonal.application.dto.UserDto;
import com.example.hexagonal.domain.model.user.User;

public class UserDtoMapperImp implements UserDtoMapper<User>{

    @Override
    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
