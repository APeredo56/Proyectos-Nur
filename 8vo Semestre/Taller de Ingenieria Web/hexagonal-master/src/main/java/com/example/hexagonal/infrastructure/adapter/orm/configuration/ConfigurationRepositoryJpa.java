package com.example.hexagonal.infrastructure.adapter.orm.configuration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public interface ConfigurationRepositoryJpa extends JpaRepository<ConfigurationEntity, String> {
}
