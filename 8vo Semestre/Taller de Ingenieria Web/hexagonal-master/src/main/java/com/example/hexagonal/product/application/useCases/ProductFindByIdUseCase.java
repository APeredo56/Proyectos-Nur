package com.example.hexagonal.product.application.useCases;

import com.example.hexagonal.product.domain.models.Product;
import com.example.hexagonal.product.domain.ports.ProductFindByIdDomainPort;
import com.example.hexagonal.product.domain.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ProductFindByIdUseCase implements ProductFindByIdDomainPort {
    private final ProductRepository productRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id);
    }

}
