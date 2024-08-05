package org.example.finalprojectmyshop.product.service.impl;

import org.example.finalprojectmyshop.product.repository.ProductPropertyRepository;
import org.example.finalprojectmyshop.product.service.ProductPropertyService;
import org.springframework.stereotype.Service;

@Service
public class ProductPropertyServiceImpl implements ProductPropertyService {

    private final ProductPropertyRepository productPropertyRepository;

    public ProductPropertyServiceImpl(ProductPropertyRepository productPropertyRepository) {
        this.productPropertyRepository = productPropertyRepository;
    }

    @Override
    public void deleteProductProperty(long id) {
        this.productPropertyRepository.deleteById(id);
    }
}
