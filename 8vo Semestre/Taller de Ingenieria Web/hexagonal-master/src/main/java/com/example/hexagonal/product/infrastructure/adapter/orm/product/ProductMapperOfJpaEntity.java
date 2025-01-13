package com.example.hexagonal.product.infrastructure.adapter.orm.product;

import com.example.hexagonal.product.application.mappers.ProductMapper;
import com.example.hexagonal.product.domain.models.Product;
import org.springframework.stereotype.Service;


@Service
public class ProductMapperOfJpaEntity implements ProductMapper<ProductEntity> {


    @Override
    public Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }

    @Override
    public ProductEntity ToEntity(Product entity) {
        return new ProductEntity(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }
}
