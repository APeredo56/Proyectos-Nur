package com.example.hexagonal.product.domain.repositories;

import com.example.hexagonal.product.domain.models.Product;

public interface ProductRepository {

    Product save(Product product);
    Product findById(Long id);

}
