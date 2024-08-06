package org.example.finalprojectmyshop.order.service;

import org.example.finalprojectmyshop.order.models.dtos.exports.SalesInfoDTO;
import org.example.finalprojectmyshop.order.models.entities.Order;
import org.example.finalprojectmyshop.order.models.entities.Sale;

public interface SaleService {
    void save(Sale sale);
    void save(Order order);
    SalesInfoDTO findSalesInfo();
}
