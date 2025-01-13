package com.example.hexagonal.infrastructure.adapter.orm.pharmacy;

import com.example.hexagonal.domain.model.pharmacy.Pharmacy;
import org.springframework.stereotype.Service;


@Service
public class PharmacyMapper {

    public PharmacyEntity toEntity(Pharmacy pharmacy) {
        PharmacyEntity pharmacyEntity = new PharmacyEntity();
        pharmacyEntity.setId(pharmacy.getId());
        pharmacyEntity.setName(pharmacy.getName());
        pharmacyEntity.setAddress(pharmacy.getAddress());
        return pharmacyEntity;
    }

    public Pharmacy toDomain(PharmacyEntity pharmacyEntity) {
        return new Pharmacy(pharmacyEntity.getId(), pharmacyEntity.getName(), pharmacyEntity.getAddress());
    }
}
