package org.example.finalprojectmyshop.order.service;

import org.example.finalprojectmyshop.order.models.dtos.exports.UserOrderDTO;

import java.util.Set;

public interface UserOrderService {
    Set<UserOrderDTO> getUserOrders();
    Set<UserOrderDTO> findUsersOrders();
}
