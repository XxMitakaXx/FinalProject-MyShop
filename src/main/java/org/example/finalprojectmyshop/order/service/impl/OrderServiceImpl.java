package org.example.finalprojectmyshop.order.service.impl;

import org.example.finalprojectmyshop.order.models.dtos.exports.CartDataDTO;
import org.example.finalprojectmyshop.order.models.dtos.imports.OrderDetailsDTO;
import org.example.finalprojectmyshop.order.models.entities.Order;
import org.example.finalprojectmyshop.order.models.entities.ProductInCartEntity;
import org.example.finalprojectmyshop.order.models.enums.OrderLogisticStatus;
import org.example.finalprojectmyshop.order.repository.OrderRepository;
import org.example.finalprojectmyshop.order.service.CartService;
import org.example.finalprojectmyshop.order.service.OrderService;
import org.example.finalprojectmyshop.order.service.ProductInCartService;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.service.UserService;
import org.example.finalprojectmyshop.user.service.impl.UserHelperService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserHelperService userHelperService;
    private final UserService userService;
    private final ProductInCartService productInCartService;

    public OrderServiceImpl(OrderRepository orderRepository, CartService cartService, UserHelperService userHelperService, UserService userService, ProductInCartService productInCartService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.userHelperService = userHelperService;
        this.userService = userService;
        this.productInCartService = productInCartService;
    }

    @Override
    public void save(OrderDetailsDTO orderDetailsDTO) {
        CartDataDTO cartDataDTO = this.cartService.loadData();
        Order order = new Order();

        order.setFirstAndLastName(orderDetailsDTO.getFirstAndLastName());
        order.setPhoneNumber(orderDetailsDTO.getPhoneNumber());
        order.setCityVillage(orderDetailsDTO.getCityVillage());
        order.setCityVillage(orderDetailsDTO.getCityVillage());
        order.setAddress(orderDetailsDTO.getAddress());
        order.setCollectingPlace(orderDetailsDTO.getCollectingPlace());
        order.setPriceForProducts(cartDataDTO.getPriceForProducts());
        order.setPriceForDelivery(cartDataDTO.getPriceForDelivery());
        order.setPriceForSum(cartDataDTO.getPriceForSum());
        order.setDate(new Date());

        UserEntity user = this.userHelperService.getUser();
        order.setBuyer(user);

        Set<Product> products = cartDataDTO.getProductsInCart()
                .stream()
                .map(productInCart -> {
                    ProductInCartEntity productInCartEntity = this.productInCartService.findById(productInCart.getId());

                    return productInCartEntity.getProduct();
                })
                .collect(Collectors.toSet());

        order.setProducts(products);
        order.setLogisticStatus(OrderLogisticStatus.SHIPPED);


        this.orderRepository.save(order);
        user.getOrders().add(order);
        this.userService.save(user);
    }
}
