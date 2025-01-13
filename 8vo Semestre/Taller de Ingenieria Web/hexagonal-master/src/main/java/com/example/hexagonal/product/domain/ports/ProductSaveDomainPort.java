package com.example.hexagonal.product.domain.ports;

import com.example.hexagonal.product.domain.models.Product;

public interface ProductSaveDomainPort {

    public Product save(Product product);
}
