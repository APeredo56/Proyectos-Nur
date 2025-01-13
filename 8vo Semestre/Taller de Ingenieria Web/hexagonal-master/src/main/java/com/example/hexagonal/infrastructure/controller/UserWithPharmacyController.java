package com.example.hexagonal.infrastructure.controller;


import com.example.hexagonal.application.dto.UserWithPharmacyDto;
import com.example.hexagonal.application.services.UserWithPharmacyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userWithPharmacy")
@AllArgsConstructor
public class UserWithPharmacyController {
    private final UserWithPharmacyService userService;

    @GetMapping("/{id}")
    public UserWithPharmacyDto findById(@PathVariable long id) {
        System.out.println("dfasfsa 20 Controller");
        UserWithPharmacyDto userWithPharmacyDto = userService.findById(id);
        System.out.println("dfasfsa 21 Controller");
        return userService.findById(id);
    }
}
