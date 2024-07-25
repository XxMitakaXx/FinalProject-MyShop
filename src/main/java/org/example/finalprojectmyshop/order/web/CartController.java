package org.example.finalprojectmyshop.order.web;

import org.example.finalprojectmyshop.order.models.dtos.exports.CartDataDTO;
import org.example.finalprojectmyshop.order.models.entities.CartEntity;
import org.example.finalprojectmyshop.order.models.entities.ProductInCartEntity;
import org.example.finalprojectmyshop.order.service.CartService;
import org.example.finalprojectmyshop.order.service.ProductInCartService;
import org.example.finalprojectmyshop.user.service.impl.UserHelperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {

    private final CartService cartService;
    private final ProductInCartService productInCartService;
    private final UserHelperService userHelperService;

    public CartController(CartService cartService, ProductInCartService productInCartService, UserHelperService userHelperService) {
        this.cartService = cartService;
        this.productInCartService = productInCartService;
        this.userHelperService = userHelperService;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {

        CartDataDTO cartDataDTO = this.cartService.loadData();
        model.addAttribute("cartData", cartDataDTO);

        return "cart-details";
    }

    //TODO
    @PostMapping("increase-product-count/{id}")
    private String increaseProductCount(@PathVariable long id) {
        CartEntity cart = this.userHelperService.getUser().getCart();
        ProductInCartEntity productInCart = this.productInCartService.findById(id);
        productInCart.setCount(productInCart.getCount() + 1);
        this.productInCartService.save(productInCart);

        cart.getProductsInCart().remove(productInCart);
        cart.getProductsInCart().add(productInCart);

        return "cart-details";
    }
}
