package com.example.hexagonal.infrastructure.controller;


import com.example.hexagonal.application.services.ConfigurationService;
import com.example.hexagonal.domain.model.configuration.Configuration;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/configuration")
@AllArgsConstructor
public class ConfigurationController {
    private final ConfigurationService configurationService;


    @GetMapping
    public Configuration getPasswordLevel() {
        return configurationService.getPasswordLevel();
    }
}
