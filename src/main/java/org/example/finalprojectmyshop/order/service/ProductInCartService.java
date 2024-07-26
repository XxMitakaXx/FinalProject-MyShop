package org.example.finalprojectmyshop.order.service;

import org.example.finalprojectmyshop.order.models.entities.ProductInCartEntity;

public interface ProductInCartService {
    void save(ProductInCartEntity productInCart);
    ProductInCartEntity findById(long id);
    void deleteById(long id);
}
