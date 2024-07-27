package org.example.finalprojectmyshop.order.web;

import org.example.finalprojectmyshop.order.models.dtos.exports.CartDataDTO;
import org.example.finalprojectmyshop.order.models.dtos.imports.OrderDetailsDTO;
import org.example.finalprojectmyshop.order.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class OrderController {

    private final CartService cartService;

    public OrderController(CartService cartService) {
        this.cartService = cartService;
    }

    @ModelAttribute("orderDetailsDTO")
    public OrderDetailsDTO orderDetailsDTO() {
        return new OrderDetailsDTO();
    }

    @GetMapping("/collecting-order-details")
    public String viewCollectingOrderDetails(Model model) {

        CartDataDTO cartDataDTO = this.cartService.loadData();


        return "collecting-order-details";
    }
}
