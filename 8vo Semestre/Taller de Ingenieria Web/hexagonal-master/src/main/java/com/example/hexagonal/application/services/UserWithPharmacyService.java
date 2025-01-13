package com.example.hexagonal.application.services;

import com.example.hexagonal.application.dto.UserWithPharmacyDto;
import com.example.hexagonal.application.repositories.UserWithPharmacyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserWithPharmacyService {
    private final UserWithPharmacyRepository userWithPharmacyRepository;

    public UserWithPharmacyDto findById(Long id) {
        return userWithPharmacyRepository.findById(id);
    }
}
