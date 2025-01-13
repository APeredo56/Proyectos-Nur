package com.example.hexagonal.infrastructure.config;

import com.example.hexagonal.application.repositories.ConfigurationRepository;
import com.example.hexagonal.application.services.ConfigurationService;
import com.example.hexagonal.infrastructure.adapter.orm.configuration.ConfigurationMapper;
import com.example.hexagonal.infrastructure.adapter.orm.configuration.ConfigurationRepositoryAdapter;
import com.example.hexagonal.infrastructure.adapter.orm.configuration.ConfigurationRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppProvider {

    @Bean
    public ConfigurationMapper configurationMapper() {
        return new ConfigurationMapper();
    }


    @Bean
    public ConfigurationRepository configurationRepository(ConfigurationRepositoryJpa configurationRepositoryJpa) {
        return new ConfigurationRepositoryAdapter(
                configurationRepositoryJpa
                , new ConfigurationMapper()
        );
    }

    @Bean
    public ConfigurationService configurationService(ConfigurationRepository repository) {
        return new ConfigurationService(repository);
    }




}
