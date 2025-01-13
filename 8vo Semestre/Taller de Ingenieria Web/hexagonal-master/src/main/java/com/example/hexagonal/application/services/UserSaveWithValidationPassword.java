package com.example.hexagonal.application.services;

import com.example.hexagonal.application.repositories.UserRepository;
import com.example.hexagonal.domain.model.configuration.Configuration;
import com.example.hexagonal.domain.model.user.User;
import com.example.hexagonal.domain.services.PasswordValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserSaveWithValidationPassword {

    private final ConfigurationService configurationService;
    private final UserRepository userRepository;

    public User save(User user) {
        Configuration passwordLevel = configurationService.getPasswordLevel();
        new PasswordValidation(user.getPassword(),
                passwordLevel.getData());

        return userRepository.save(user);

    }
}
