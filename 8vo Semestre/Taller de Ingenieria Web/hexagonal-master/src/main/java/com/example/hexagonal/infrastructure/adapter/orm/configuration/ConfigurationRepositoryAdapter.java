package com.example.hexagonal.infrastructure.adapter.orm.configuration;

import com.example.hexagonal.application.repositories.ConfigurationRepository;
import com.example.hexagonal.domain.enums.ConfigurationKeyEnum;
import com.example.hexagonal.domain.enums.ConfigurationValuePasswordLevelEnum;
import com.example.hexagonal.domain.model.configuration.Configuration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@AllArgsConstructor

public class ConfigurationRepositoryAdapter implements ConfigurationRepository {
    private final ConfigurationRepositoryJpa configurationRepositoryJpa;
    private ConfigurationMapper configurationMapper;

    @Override
    public Configuration getPasswordLevel() {

        Optional<ConfigurationEntity> optConfigPasswordLevel = configurationRepositoryJpa.findById(
                ConfigurationKeyEnum.CONFIG_PASSWORD_LEVEL.toString()
        );

        //Agrega la configuración de rigurosidad de contraseña si no existe
        if (!optConfigPasswordLevel.isPresent()) {
            ConfigurationEntity configPasswordLevel = new ConfigurationEntity();
            configPasswordLevel.setIdString(ConfigurationKeyEnum.CONFIG_PASSWORD_LEVEL.toString());
            configPasswordLevel.setData(ConfigurationValuePasswordLevelEnum.MEAK_PASSWORD.toString());
            configurationRepositoryJpa.save(configPasswordLevel);

            return configurationMapper.toDomain(configPasswordLevel);
        }
        return configurationMapper.toDomain(optConfigPasswordLevel.get());

    }
}
