package org.example.finalprojectmyshop.order.web;

import jakarta.validation.Valid;
import org.example.finalprojectmyshop.order.models.dtos.exports.CartDataDTO;
import org.example.finalprojectmyshop.order.models.dtos.imports.OrderDetailsDTO;
import org.example.finalprojectmyshop.order.models.entities.Order;
import org.example.finalprojectmyshop.order.models.enums.CollectingPlace;
import org.example.finalprojectmyshop.order.service.CartService;
import org.example.finalprojectmyshop.order.service.OrderService;
import org.example.finalprojectmyshop.user.service.impl.UserHelperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@Controller
public class OrderController {

    private final CartService cartService;
    private final OrderService orderService;
    private final UserHelperService userHelperService;
    private final CollectingPlace[] collectingPlaces = CollectingPlace.values();

    public OrderController(CartService cartService, OrderService orderService, UserHelperService userHelperService) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.userHelperService = userHelperService;
    }

    @ModelAttribute("orderDetailsDTO")
    public OrderDetailsDTO orderDetailsDTO() {
        return new OrderDetailsDTO();
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

        this.orderService.save(orderDetailsDTO);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderDetailsDTO", orderDetailsDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderDetailsDTO", bindingResult);

            return "redirect:/collecting-order-details";
        }

        // TODO
        return "redirect:/";
    }

    @GetMapping("/user-orders")
    public String viewUserOrders(Model model) {
        Set<Order> userOrders = this.userHelperService.getUser().getOrders();

        model.addAttribute("userOrders", userOrders);

        return "user-orders";
    }
}
