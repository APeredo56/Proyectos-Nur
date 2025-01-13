package com.example.hexagonal.infrastructure.adapter.orm.pharmacy;

import com.example.hexagonal.application.repositories.PharmacyRepository;
import com.example.hexagonal.domain.model.pharmacy.Pharmacy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PharmacyRepositoryAdapter implements PharmacyRepository {
    private final PhamacyRepositoryJpa phamacyRepositoryJpa;
    private  final PharmacyMapper pharmacyMapper;

    @Override
    public Pharmacy findById(long id) {
        return pharmacyMapper.toDomain(
            phamacyRepositoryJpa.findById(id).get()
        );
    }

    @Override
    public Pharmacy save(Pharmacy pharmacy) {
        PharmacyEntity pharmacyEntity = pharmacyMapper.toEntity(pharmacy);
        phamacyRepositoryJpa.save(pharmacyEntity);
        return pharmacyMapper.toDomain(pharmacyEntity);
    }
}
