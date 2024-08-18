package org.example.finalprojectmyshop.order.service;

import org.example.finalprojectmyshop.order.models.dtos.exports.UserOrderDTO;
import org.example.finalprojectmyshop.order.models.dtos.exports.UserOrderDetailsDTO;
import org.example.finalprojectmyshop.order.models.dtos.imports.OrderDetailsDTO;
import org.example.finalprojectmyshop.order.models.entities.Order;

public interface OrderCrudService {
    void save(OrderDetailsDTO orderDetailsDTO);
    void save(Order order);
    UserOrderDetailsDTO findOrderDetails(long id);
    String orderLogisticStatus(UserOrderDTO userOrderDTO);
    Order findOrderEntity(long id);
    void deleteOrder(long id);
}
