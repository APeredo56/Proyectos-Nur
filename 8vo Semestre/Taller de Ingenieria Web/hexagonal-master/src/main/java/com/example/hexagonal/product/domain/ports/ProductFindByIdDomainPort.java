package com.example.hexagonal.product.domain.ports;

import com.example.hexagonal.product.domain.models.Product;

public interface ProductFindByIdDomainPort {

    public Product findById(Long id);
}
