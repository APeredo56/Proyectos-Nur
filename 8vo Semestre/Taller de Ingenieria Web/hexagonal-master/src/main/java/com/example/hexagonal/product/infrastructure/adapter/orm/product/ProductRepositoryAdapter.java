package com.example.hexagonal.product.infrastructure.adapter.orm.product;

import com.example.hexagonal.product.domain.models.Product;
import com.example.hexagonal.product.domain.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductRepositoryJpa productJpaRepository;
    private final ProductMapperOfJpaEntity mapper;

    @Override
    public Product save(Product product) {
        ProductEntity entity = mapper.ToEntity(product);

        return mapper.toDomain(
                productJpaRepository.save(entity)
        );
    }

    @Override
    public Product findById(Long id) {
        ProductEntity entity = productJpaRepository.findById(id).get();
        return mapper.toDomain(entity);
    }
}
