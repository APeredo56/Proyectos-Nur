package com.example.hexagonal.product.infrastructure.controller;


import com.example.hexagonal.product.application.port.ProductServicePort;
import com.example.hexagonal.product.domain.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private final ProductServicePort productServicePort;

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productServicePort.findById(id);
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productServicePort.save(product);
    }
}
