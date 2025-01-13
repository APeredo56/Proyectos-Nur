package com.example.hexagonal.product.application.services;

import com.example.hexagonal.product.application.port.ProductServicePort;
import com.example.hexagonal.product.domain.models.Product;
import com.example.hexagonal.product.domain.ports.ProductFindByIdDomainPort;
import com.example.hexagonal.product.domain.ports.ProductSaveDomainPort;
import com.example.hexagonal.product.domain.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService implements ProductServicePort {
    private final ProductSaveDomainPort productSaveDomainPort;
    private final ProductFindByIdDomainPort productFindByIdDomainPort;


    @Override
    public Product findById(Long id) {
        return productFindByIdDomainPort.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productSaveDomainPort.save(product);
    }
}
