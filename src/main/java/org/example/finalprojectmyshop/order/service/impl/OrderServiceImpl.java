package org.example.finalprojectmyshop.order.service.impl;

import org.example.finalprojectmyshop.order.models.dtos.exports.*;
import org.example.finalprojectmyshop.order.models.dtos.imports.OrderDetailsDTO;
import org.example.finalprojectmyshop.order.models.entities.*;
import org.example.finalprojectmyshop.order.models.enums.OrderLogisticStatus;
import org.example.finalprojectmyshop.order.repository.OrderRepository;
import org.example.finalprojectmyshop.order.service.CartService;
import org.example.finalprojectmyshop.order.service.OrderService;
import org.example.finalprojectmyshop.order.service.ProductInCartService;
import org.example.finalprojectmyshop.order.service.ProductInOrderService;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.product.service.ProductService;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.service.UserService;
import org.example.finalprojectmyshop.user.service.impl.UserHelperService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserHelperService userHelperService;
    private final UserService userService;
    private final ProductInCartService productInCartService;
    private final ProductService productService;
    private final ProductInOrderService productInOrderService;

    public OrderServiceImpl(OrderRepository orderRepository, CartService cartService, UserHelperService userHelperService, UserService userService, ProductInCartService productInCartService, ProductService productService, ProductInOrderService productInOrderService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.userHelperService = userHelperService;
        this.userService = userService;
        this.productInCartService = productInCartService;
        this.productService = productService;
        this.productInOrderService = productInOrderService;
    }

    @Override
    public void save(OrderDetailsDTO orderDetailsDTO) {
        CartDataDTO cartDataDTO = this.cartService.loadData();
        Order order = new Order();

        order.setFirstAndLastName(orderDetailsDTO.getFirstAndLastName());
        order.setPhoneNumber(orderDetailsDTO.getPhoneNumber());
        order.setCityVillage(orderDetailsDTO.getCityVillage());
        order.setAddress(orderDetailsDTO.getAddress());
        order.setCollectingPlace(orderDetailsDTO.getCollectingPlace());
        order.setPriceForProducts(cartDataDTO.getPriceForProducts());
        order.setPriceForDelivery(cartDataDTO.getPriceForDelivery());
        order.setPriceForSum(cartDataDTO.getPriceForSum());
        order.setOrderDate(new Date());

        UserEntity user = this.userHelperService.getUser();
        order.setBuyer(user);

        cartDataDTO.getProductsInCart()
                .forEach(product -> {
                    this.productService.decreaseProductQuantity(product.getProductId(), product.getCount());
                });

        Set<Product> productInOrderEntities = cartDataDTO.getProductsInCart()
                .stream()
                .map(productInCart -> this.productService.findProductEntityById(productInCart.getProductId()))
                .collect(Collectors.toSet());

        order.setProducts(productInOrderEntities);
        order.setLogisticStatus(OrderLogisticStatus.PROCESSING);

        this.orderRepository.save(order);
        user.getOrders().add(order);
        this.userService.save(user);

        this.deleteCart();
    }

    @Override
    public void save(Order order) {
        this.orderRepository.save(order);
    }

    private ProductInOrderEntity toProductInOrderEntity(Product product, CartProductDTO productInCart) {
        ProductInOrderEntity productInOrderEntity = new ProductInOrderEntity();
        productInOrderEntity.setProduct(product);
        productInOrderEntity.setCount(productInCart.getCount());

        return productInOrderEntity;
    }

    private void deleteCart() {
        UserEntity user = this.userHelperService.getUser();
        CartEntity cart = user.getCart();

        Set<Long> productInCartEntitiesIds = cart.getProductsInCart()
                .stream()
                .map(ProductInCartEntity::getId)
                .collect(Collectors.toSet());

        Set<ProductInCartEntity> emptyCart = user.getCart().getProductsInCart()
                .stream()
                .filter(productInCartEntities -> !productInCartEntitiesIds.contains(productInCartEntities.getId()))
                .collect(Collectors.toSet());

        cart.setProductsInCart(emptyCart);

        CartEntity cartEntity = new CartEntity();
        this.cartService.save(cartEntity);
        user.setCart(cartEntity);
        this.userService.save(user);

        this.cartService.save(cart);
        this.cartService.deleteById(cart.getId());

        productInCartEntitiesIds.forEach(this.productInCartService::deleteById);
    }

    @Override
    public Set<UserOrderDTO> getUserOrders() {
        Set<Order> orders = this.userHelperService.getUser().getOrders();

        return orders
                .stream()
                .map(this::toUserOrderDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public UserOrderDetailsDTO findOrderDetails(long id) {
        Optional<Order> optional = this.orderRepository.findById(id);

        if (optional.isEmpty()) {
            return new UserOrderDetailsDTO();
        }

        UserOrderDetailsDTO userOrderDetailsDTO = this.toUserOrderDetailsDTO(optional.get());

        return userOrderDetailsDTO;
    }

    @Override
    public Set<UserOrderDTO> findUsersOrders() {
        Set<Order> orders = this.orderRepository.findOrdersForDelivery();

        return orders
                .stream()
                .map(this::toUserOrderDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public String orderLogisticStatus(UserOrderDTO userOrderDTO) {

        if (userOrderDTO.getLogisticStatus() == OrderLogisticStatus.PROCESSING) {
            return "PROCESSING";
        } else if (userOrderDTO.getLogisticStatus() == OrderLogisticStatus.SHIPPED) {
            return "SHIPPED";
        } else if (userOrderDTO.getLogisticStatus() == OrderLogisticStatus.IN_OFFICE) {
            return "IN_OFFICE";
        } else {
            return "RECEIVED";
        }
    }

    @Override
    public Order findOrderEntity(long id) {
        Optional<Order> optional = this.orderRepository.findById(id);

        if (optional.isEmpty()) {
            return new Order();
        }

        Order order = optional.get();

        return order;
    }

    @Override
    public void deleteOrder(long id) {
        this.orderRepository.deleteById(id);
    }

    private UserOrderDTO toUserOrderDTO(Order order) {
        UserOrderDTO userOrderDTO = new UserOrderDTO();

        userOrderDTO.setId(order.getId());
        userOrderDTO.setPriceForSum(order.getPriceForSum());
        userOrderDTO.setOrderDate(order.getOrderDate());
        userOrderDTO.setLogisticStatus(order.getLogisticStatus());
        userOrderDTO.setDeliveryDate(order.getDeliveryDate());

        return userOrderDTO;
    }

    private UserOrderDetailsDTO toUserOrderDetailsDTO(Order order) {
        UserOrderDetailsDTO userOrderDetailsDTO = new UserOrderDetailsDTO();

        userOrderDetailsDTO.setFirstAndLastName(order.getFirstAndLastName());
        userOrderDetailsDTO.setPhoneNumber(order.getPhoneNumber());
        userOrderDetailsDTO.setCityVillage(order.getCityVillage());
        userOrderDetailsDTO.setAddress(order.getAddress());
        userOrderDetailsDTO.setCollectingPlace(order.getCollectingPlace());
        userOrderDetailsDTO.setPriceForProducts(order.getPriceForProducts());
        userOrderDetailsDTO.setPriceForDelivery(order.getPriceForDelivery());
        userOrderDetailsDTO.setPriceForSum(order.getPriceForSum());
        userOrderDetailsDTO.setOrderDate(order.getOrderDate());
        userOrderDetailsDTO.setDeliveryDate(order.getDeliveryDate());

        Set<OrderDetailsProductDTO> productDTOS = order.getProducts()
                .stream()
                .map(this::toOrderDetailsProductDTO)
                .collect(Collectors.toSet());

        userOrderDetailsDTO.setProducts(productDTOS);

        userOrderDetailsDTO.setOrderLogisticStatus(order.getLogisticStatus());

        return userOrderDetailsDTO;
    }

    private OrderDetailsProductDTO toOrderDetailsProductDTO(Product product) {
        OrderDetailsProductDTO orderDetailsProductDTO = new OrderDetailsProductDTO();

        orderDetailsProductDTO.setProduct(product);
//        orderDetailsProductDTO.setCount(productInOrderEntity.getCount());

        return orderDetailsProductDTO;
    }

}
