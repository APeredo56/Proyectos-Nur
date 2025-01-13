package com.example.hexagonal.application.repositories;

import com.example.hexagonal.domain.enums.ConfigurationValuePasswordLevelEnum;
import com.example.hexagonal.domain.model.configuration.Configuration;

public interface ConfigurationRepository {

    public Configuration getPasswordLevel();
}
