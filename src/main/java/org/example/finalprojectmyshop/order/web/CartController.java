package org.example.finalprojectmyshop.order.web;

import org.example.finalprojectmyshop.order.models.dtos.exports.CartDataDTO;
import org.example.finalprojectmyshop.order.models.entities.CartEntity;
import org.example.finalprojectmyshop.order.models.entities.ProductInCartEntity;
import org.example.finalprojectmyshop.order.service.CartService;
import org.example.finalprojectmyshop.order.service.ProductInCartService;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.service.UserService;
import org.example.finalprojectmyshop.user.service.impl.UserHelperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CartController {

    private final CartService cartService;
    private final ProductInCartService productInCartService;
    private final UserHelperService userHelperService;
    private final UserService userService;

    public CartController(CartService cartService, ProductInCartService productInCartService, UserHelperService userHelperService, UserService userService) {
        this.cartService = cartService;
        this.productInCartService = productInCartService;
        this.userHelperService = userHelperService;
        this.userService = userService;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {

        CartDataDTO cartDataDTO = this.cartService.loadData();
        model.addAttribute("cartData", cartDataDTO);

        return "cart-details";
    }

    @PutMapping("/increase-product-count/{id}")
    private String increaseProductCount(@PathVariable long id, RedirectAttributes redirectAttributes) {
        UserEntity user = this.userHelperService.getUser();
        CartEntity cart = user.getCart();
        ProductInCartEntity productInCart = this.productInCartService.findById(id);
        productInCart.setCount(productInCart.getCount() + 1);
        this.productInCartService.save(productInCart);

        cart.getProductsInCart().remove(productInCart);
        cart.getProductsInCart().add(productInCart);
        this.cartService.save(cart);

        user.setCart(cart);
        this.userService.save(user);

        CartDataDTO cartDataDTO = this.cartService.loadData();
        redirectAttributes.addFlashAttribute("cartData", cartDataDTO);

        return "redirect:/cart";
    }

    @PutMapping("/decrease-product-count/{id}")
    private String decreaseProductCount(@PathVariable long id, RedirectAttributes redirectAttributes) {
        UserEntity user = this.userHelperService.getUser();
        CartEntity cart = user.getCart();
        ProductInCartEntity productInCart = this.productInCartService.findById(id);

        if (productInCart.getCount() > 1) {
            productInCart.setCount(productInCart.getCount() - 1);
            this.productInCartService.save(productInCart);

            cart.getProductsInCart().remove(productInCart);
            cart.getProductsInCart().add(productInCart);

            this.cartService.save(cart);

            this.userService.save(user);
        }

        CartDataDTO cartDataDTO = this.cartService.loadData();
        redirectAttributes.addFlashAttribute("cartData", cartDataDTO);

        return "redirect:/cart";
    }}
