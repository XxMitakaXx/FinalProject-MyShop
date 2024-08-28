package org.example.finalprojectmyshop.order.service;

import org.example.finalprojectmyshop.order.models.dtos.exports.SaleInfoDTO;
import org.example.finalprojectmyshop.order.models.entities.Order;
import org.example.finalprojectmyshop.order.models.entities.Sale;

import java.util.List;

public interface SaleService {
    void save(Sale sale);
    void save(Order order);
    List<SaleInfoDTO> findSalesInfo();
    boolean deleteSale(long id);
    List<SaleInfoDTO> getSales();
}
