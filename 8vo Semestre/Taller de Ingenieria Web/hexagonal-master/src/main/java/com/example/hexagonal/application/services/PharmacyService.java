package com.example.hexagonal.application.services;

import com.example.hexagonal.application.repositories.PharmacyRepository;
import com.example.hexagonal.domain.model.pharmacy.Pharmacy;
import org.springframework.stereotype.Service;

@Service
public class PharmacyService {
    private  final PharmacyRepository pharmacyRepository;

    public PharmacyService(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public Pharmacy findById(long id) {
        return pharmacyRepository.findById(id);
    }

    public Pharmacy save(Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }


}
