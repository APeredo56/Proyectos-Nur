package com.example.hexagonal.infrastructure.controller;


import com.example.hexagonal.application.dto.UserDto;
import com.example.hexagonal.application.services.UserSaveWithValidationPassword;
import com.example.hexagonal.application.services.UserService;
import com.example.hexagonal.domain.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")

@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserSaveWithValidationPassword userSaveWithValidationPassword;

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return userSaveWithValidationPassword.save(user);
    }

}
