package com.example.hexagonal.product.application.mappers;

import com.example.hexagonal.product.domain.models.Product;

public interface ProductMapper <T> {

    public Product toDomain(T entity);
    public T ToEntity(Product entity);

}
