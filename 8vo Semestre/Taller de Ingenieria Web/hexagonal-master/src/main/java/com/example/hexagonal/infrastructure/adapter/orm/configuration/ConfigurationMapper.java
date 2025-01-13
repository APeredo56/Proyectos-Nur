package com.example.hexagonal.infrastructure.adapter.orm.configuration;

import com.example.hexagonal.domain.model.configuration.Configuration;
import org.springframework.stereotype.Service;



public class ConfigurationMapper {

    public ConfigurationEntity toEntity(Configuration configuration) {
        return new ConfigurationEntity(
                configuration.getIdString(),
                configuration.getData()
        );

    }

    public Configuration toDomain(ConfigurationEntity configurationEntity) {
        return new Configuration(configurationEntity.getIdString(), configurationEntity.getData());
    }
}
