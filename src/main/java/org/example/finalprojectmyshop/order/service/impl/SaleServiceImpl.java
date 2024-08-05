package org.example.finalprojectmyshop.order.service.impl;

import org.example.finalprojectmyshop.order.models.entities.Order;
import org.example.finalprojectmyshop.order.models.entities.Sale;
import org.example.finalprojectmyshop.order.repository.SaleRepository;
import org.example.finalprojectmyshop.order.service.SaleService;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.product.service.ProductService;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.service.UserService;
import org.example.finalprojectmyshop.user.service.impl.UserHelperService;
import org.springframework.stereotype.Service;

import java.util.Date;

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

        UserEntity user = this.userService.findUserByEmail(order.getBuyer().getEmail());
        sale.setBuyer(user);

        order.getProducts().forEach(product -> sale.getProducts().add(product));
        sale.setCityVillage(order.getCityVillage());
        sale.setAddress(order.getAddress());

        this.saleRepository.save(sale);
    }
}
