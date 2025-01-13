package com.example.hexagonal.infrastructure.controller;


import com.example.hexagonal.application.services.PharmacyService;
import com.example.hexagonal.domain.model.pharmacy.Pharmacy;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pharmacy")
@AllArgsConstructor
public class PharmacyController {

    private final PharmacyService pharmacyService;

    @GetMapping("/{id}")
    public Pharmacy findById(@PathVariable long id) {
        return pharmacyService.findById(id);
    }

    @PostMapping
    public Pharmacy save(@RequestBody Pharmacy pharmacy) {
        return pharmacyService.save(pharmacy);
    }



}
