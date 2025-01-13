package com.example.hexagonal.product.application.port;

import com.example.hexagonal.product.domain.ports.ProductFindByIdDomainPort;
import com.example.hexagonal.product.domain.ports.ProductSaveDomainPort;

public interface ProductServicePort extends ProductFindByIdDomainPort, ProductSaveDomainPort {
}
