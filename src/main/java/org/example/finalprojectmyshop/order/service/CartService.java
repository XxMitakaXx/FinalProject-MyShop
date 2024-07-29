package org.example.finalprojectmyshop.order.service;

import org.example.finalprojectmyshop.order.models.dtos.exports.CartDataDTO;
import org.example.finalprojectmyshop.order.models.entities.CartEntity;

public interface CartService {
    void save(CartEntity cart);
    void deleteById(long id);
    CartDataDTO loadData();
}
