package org.example.finalprojectmyshop.order.service.impl;

import org.example.finalprojectmyshop.order.models.dtos.exports.SaleInfoDTO;
import org.example.finalprojectmyshop.order.models.dtos.exports.SaleInfoProductDTO;
import org.example.finalprojectmyshop.order.models.dtos.exports.SalesInfoDTO;
import org.example.finalprojectmyshop.order.models.entities.Order;
import org.example.finalprojectmyshop.order.models.entities.Sale;
import org.example.finalprojectmyshop.order.repository.SaleRepository;
import org.example.finalprojectmyshop.order.service.SaleService;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final UserService userService;

    public SaleServiceImpl(SaleRepository saleRepository, UserService userService) {
        this.saleRepository = saleRepository;
        this.userService = userService;
    }

    @Override
    public void save(Sale sale) {
        this.saleRepository.save(sale);
    }

    @Override
    public void save(Order order) {
        Sale sale = new Sale();

        sale.setDate(new Date());

        order.getProducts().forEach(product -> sale.getProducts().add(product));
        sale.setSum(order.getPriceForSum());
        sale.setCityVillage(order.getCityVillage());
        sale.setAddress(order.getAddress());

        this.saleRepository.save(sale);
    }

    @Override
    public SalesInfoDTO findSalesInfo() {
        List<Sale> sales = this.saleRepository.findAll();
        SalesInfoDTO salesInfoDTO = new SalesInfoDTO();

        sales.forEach(sale -> {
            SaleInfoDTO saleInfoDTO = this.toSaleInfoDTO(sale);
            salesInfoDTO.getSalesInfos().add(saleInfoDTO);
        });

        return salesInfoDTO;
    }

    private SaleInfoDTO toSaleInfoDTO(Sale sale) {
        SaleInfoDTO saleInfoDTO = new SaleInfoDTO();

        saleInfoDTO.setId(sale.getId());
        saleInfoDTO.setDate(sale.getDate());

        sale.getProducts()
                .forEach(product -> {
                    SaleInfoProductDTO saleInfoProductDTO = this.toSaleInfoProductDTO(product);
                    saleInfoDTO.getProducts().add(saleInfoProductDTO);
                });

        saleInfoDTO.setSum(sale.getSum());
        saleInfoDTO.setCityVillage(sale.getCityVillage());
        saleInfoDTO.setAddress(sale.getAddress());

        return saleInfoDTO;
    }

    private SaleInfoProductDTO toSaleInfoProductDTO(Product product) {
        SaleInfoProductDTO saleInfoProductDTO = new SaleInfoProductDTO();

        saleInfoProductDTO.setName(product.getName());
        saleInfoProductDTO.setPrice(product.getPrice());
        saleInfoProductDTO.setImageUrl(product.getMainImage().getImageUrl());

        return saleInfoProductDTO;
    }
}
