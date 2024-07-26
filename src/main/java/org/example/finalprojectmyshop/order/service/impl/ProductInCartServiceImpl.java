package org.example.finalprojectmyshop.order.service.impl;

import org.example.finalprojectmyshop.order.models.entities.ProductInCartEntity;
import org.example.finalprojectmyshop.order.repository.ProductInCartRepository;
import org.example.finalprojectmyshop.order.service.ProductInCartService;
import org.springframework.stereotype.Service;

@Service
public class ProductInCartServiceImpl implements ProductInCartService {

    private final ProductInCartRepository productInCartRepository;

    public ProductInCartServiceImpl(ProductInCartRepository productInCartRepository) {
        this.productInCartRepository = productInCartRepository;
    }

    @Override
    public void save(ProductInCartEntity productInCart) {
        this.productInCartRepository.save(productInCart);
    }

    @Override
    public ProductInCartEntity findById(long id) {
        return this.productInCartRepository.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        this.productInCartRepository.deleteById(id);
    }
}
