package org.example.finalprojectmyshop.order.web;

import jakarta.validation.Valid;
import org.example.finalprojectmyshop.order.models.dtos.exports.CartDataDTO;
import org.example.finalprojectmyshop.order.models.dtos.exports.UserOrderDTO;
import org.example.finalprojectmyshop.order.models.dtos.exports.UserOrderDetailsDTO;
import org.example.finalprojectmyshop.order.models.dtos.imports.OrderDetailsDTO;
import org.example.finalprojectmyshop.order.models.dtos.imports.SearchProductByNameDTO;
import org.example.finalprojectmyshop.order.models.entities.Order;
import org.example.finalprojectmyshop.order.models.enums.CollectingPlace;
import org.example.finalprojectmyshop.order.models.enums.OrderLogisticStatus;
import org.example.finalprojectmyshop.order.service.CartService;
import org.example.finalprojectmyshop.order.service.OrderCrudService;
import org.example.finalprojectmyshop.order.service.SaleService;
import org.example.finalprojectmyshop.order.service.UserOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.Set;

@Controller
public class OrderController {
    private final CartService cartService;
    private final OrderCrudService orderCrudService;
    private final UserOrderService userOrderService;
    private final SaleService saleService;
    private final CollectingPlace[] collectingPlaces = CollectingPlace.values();
    private final OrderLogisticStatus[] orderLogisticStatuses = OrderLogisticStatus.values();

    public OrderController(CartService cartService, OrderCrudService orderCrudService, UserOrderService userOrderService, SaleService saleService) {
        this.cartService = cartService;
        this.orderCrudService = orderCrudService;
        this.userOrderService = userOrderService;
        this.saleService = saleService;
    }

    @ModelAttribute("orderDetailsDTO")
    public OrderDetailsDTO orderDetailsDTO() {
        return new OrderDetailsDTO();
    }

    @ModelAttribute("searchProductByNameDTO")
    public SearchProductByNameDTO searchProductByNameDTO() {
        return new SearchProductByNameDTO();
    }

    @GetMapping("/collecting-order-details")
    public String viewCollectingOrderDetails(Model model) {

        model.addAttribute("collectingPlaces", collectingPlaces);

        CartDataDTO cartDataDTO = this.cartService.loadData();
        model.addAttribute("cartDataDTO", cartDataDTO);

        return "collecting-order-details";
    }

    @PostMapping("/collecting-order-details")
    public String processCollectingOrderDetails(
            @Valid OrderDetailsDTO orderDetailsDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderDetailsDTO", orderDetailsDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderDetailsDTO", bindingResult);

            return "redirect:/collecting-order-details";
        }

        this.orderCrudService.save(orderDetailsDTO);

        return "redirect:/user-orders";
    }

    @GetMapping("/user-orders")
    public String viewUserOrders(Model model) {
        Set<UserOrderDTO> userOrders = this.userOrderService.getUserOrders();

        model.addAttribute("userOrders", userOrders);

        return "user-orders";
    }

    @GetMapping("/user-order-details/{id}")
    public String viewUserOrderDetails(@PathVariable long id, Model model) {
        UserOrderDetailsDTO orderDetails = this.orderCrudService.findOrderDetails(id);

        model.addAttribute("orderDetails", orderDetails);

        return "user-order-details";
    }

    @GetMapping("/users-orders")
    public String viewUsersOrders(Model model) {

        Set<UserOrderDTO> usersOrders = this.userOrderService.findUsersOrders();

        model.addAttribute("usersOrders", usersOrders);

        return "users-orders";
    }

    @GetMapping("/users-orders-details/{id}")
    public String viewUsersOrdersDetails(@PathVariable long id, Model model) {
        UserOrderDetailsDTO orderDetails = this.orderCrudService.findOrderDetails(id);

        model.addAttribute("orderDetails", orderDetails);

        return "user-order-details";
    }

    @PutMapping("/move-order-to-shipped/{id}")
    public String processMoveOrderToShipped(@PathVariable("id") long id) {

        Order order = this.orderCrudService.findOrderEntity(id);
        order.setLogisticStatus(OrderLogisticStatus.SHIPPED);
        this.orderCrudService.save(order);

        return "redirect:/users-orders";
    }

    @PutMapping("/move-order-to-in-office/{id}")
    public String processMoveOrderToInOffice(@PathVariable("id") long id) {

        Order order = this.orderCrudService.findOrderEntity(id);
        order.setLogisticStatus(OrderLogisticStatus.IN_OFFICE);
        this.orderCrudService.save(order);

        return "redirect:/users-orders";
    }

    @PutMapping("/move-order-to-received/{id}")
    public String processMoveOrderToReceived(@PathVariable("id") long id) {

        Order order = this.orderCrudService.findOrderEntity(id);
        order.setLogisticStatus(OrderLogisticStatus.RECEIVED);
        order.setDeliveryDate(new Date());
        this.orderCrudService.save(order);

        this.saleService.save(order);

        return "redirect:/users-orders";
    }
}