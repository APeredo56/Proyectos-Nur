package com.example.hexagonal.application.mapper;

import com.example.hexagonal.application.dto.UserDto;
import com.example.hexagonal.domain.model.user.User;

public interface UserDtoMapper<T> {

    public UserDto toDto(T user) ;
}
