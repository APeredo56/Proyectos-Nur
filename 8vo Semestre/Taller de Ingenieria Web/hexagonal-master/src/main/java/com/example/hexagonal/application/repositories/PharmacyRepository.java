package com.example.hexagonal.application.repositories;

import com.example.hexagonal.domain.model.pharmacy.Pharmacy;
import org.springframework.stereotype.Service;

@Service
public interface PharmacyRepository {

    public Pharmacy findById(long id);
    public Pharmacy save(Pharmacy pharmacy);
}
