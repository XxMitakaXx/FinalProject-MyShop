package org.example.finalprojectmyshop.order.service;

import org.example.finalprojectmyshop.order.models.dtos.exports.UserOrderDetailsDTO;
import org.example.finalprojectmyshop.order.models.dtos.exports.UserOrderDTO;
import org.example.finalprojectmyshop.order.models.dtos.imports.OrderDetailsDTO;

import java.util.Set;

public interface OrderService {
    void save(OrderDetailsDTO orderDetailsDTO);
    Set<UserOrderDTO> getUserOrders();
    UserOrderDetailsDTO findOrderDetails(long id);
    Set<UserOrderDTO> findUsersOrders();
}
