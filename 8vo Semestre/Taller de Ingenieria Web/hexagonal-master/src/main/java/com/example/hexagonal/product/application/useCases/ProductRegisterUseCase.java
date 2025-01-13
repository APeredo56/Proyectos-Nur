package com.example.hexagonal.product.application.useCases;

import com.example.hexagonal.product.domain.models.Product;
import com.example.hexagonal.product.domain.ports.ProductSaveDomainPort;
import com.example.hexagonal.product.domain.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductRegisterUseCase implements ProductSaveDomainPort {
    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
