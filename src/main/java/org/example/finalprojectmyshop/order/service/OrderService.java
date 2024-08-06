package org.example.finalprojectmyshop.order.service;

import org.example.finalprojectmyshop.order.models.dtos.exports.UserOrderDetailsDTO;
import org.example.finalprojectmyshop.order.models.dtos.exports.UserOrderDTO;
import org.example.finalprojectmyshop.order.models.dtos.imports.OrderDetailsDTO;
import org.example.finalprojectmyshop.order.models.entities.Order;

import java.util.Set;

public interface OrderService {
    void save(OrderDetailsDTO orderDetailsDTO);
    void save(Order order);
    Set<UserOrderDTO> getUserOrders();
    UserOrderDetailsDTO findOrderDetails(long id);
    Set<UserOrderDTO> findUsersOrders();
    String orderLogisticStatus(UserOrderDTO userOrderDTO);
    Order findOrderEntity(long id);
    void deleteOrder(long id);
}
