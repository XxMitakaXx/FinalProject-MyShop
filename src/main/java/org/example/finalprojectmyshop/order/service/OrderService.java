package org.example.finalprojectmyshop.order.service;

import org.example.finalprojectmyshop.order.models.dtos.imports.OrderDetailsDTO;

public interface OrderService {
    void save(OrderDetailsDTO orderDetailsDTO);
}
