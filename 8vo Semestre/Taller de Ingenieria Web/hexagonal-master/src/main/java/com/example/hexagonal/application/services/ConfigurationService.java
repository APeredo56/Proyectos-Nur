package com.example.hexagonal.application.services;

import com.example.hexagonal.application.repositories.ConfigurationRepository;
import com.example.hexagonal.domain.model.configuration.Configuration;
import com.example.hexagonal.infrastructure.adapter.orm.configuration.ConfigurationMapper;
import com.example.hexagonal.infrastructure.adapter.orm.configuration.ConfigurationRepositoryAdapter;
import com.example.hexagonal.infrastructure.adapter.orm.configuration.ConfigurationRepositoryJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;


    public Configuration getPasswordLevel() {
        return configurationRepository.getPasswordLevel();
    }


}
