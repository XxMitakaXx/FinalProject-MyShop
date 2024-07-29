package org.example.finalprojectmyshop.order.service.impl;

import org.example.finalprojectmyshop.order.models.entities.ProductInOrderEntity;
import org.example.finalprojectmyshop.order.repository.ProductInOrderRepository;
import org.example.finalprojectmyshop.order.service.ProductInOrderService;
import org.springframework.stereotype.Service;

@Service
public class ProductInOrderServiceImpl implements ProductInOrderService {

    private final ProductInOrderRepository productInOrderRepository;

    public ProductInOrderServiceImpl(ProductInOrderRepository productInOrderRepository) {
        this.productInOrderRepository = productInOrderRepository;
    }

    @Override
    public void save(ProductInOrderEntity productInOrderEntity) {
        this.productInOrderRepository.save(productInOrderEntity);
    }
}
