package com.example.hexagonal.infrastructure.adapter.orm.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {


}
