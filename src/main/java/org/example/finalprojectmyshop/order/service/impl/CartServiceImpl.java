package org.example.finalprojectmyshop.order.service.impl;

import org.example.finalprojectmyshop.order.models.dtos.exports.CartDataDTO;
import org.example.finalprojectmyshop.order.models.dtos.exports.CartProductDTO;
import org.example.finalprojectmyshop.order.models.entities.CartEntity;
import org.example.finalprojectmyshop.order.repository.CartRepository;
import org.example.finalprojectmyshop.order.service.CartService;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.user.service.impl.UserHelperService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserHelperService userHelperService;

    public CartServiceImpl(CartRepository cartRepository, UserHelperService userHelperService) {
        this.cartRepository = cartRepository;
        this.userHelperService = userHelperService;
    }

    @Override
    public void save(CartEntity cart) {
        this.cartRepository.save(cart);
    }

    @Override
    public CartDataDTO loadData() {
        CartDataDTO cartDataDTO = new CartDataDTO();

        CartEntity cart = this.userHelperService.getUser().getCart();

        Set<CartProductDTO> cartProductDTOs = cart.getProductsInCart()
                .stream()
                .map(productInCartEntity -> this.toCartProductDTO(productInCartEntity.getProduct()))
                .collect(Collectors.toSet());


        cartDataDTO.setProductsInCart(cartProductDTOs);

        double priceForProducts = cart.getProductsInCart()
                .stream()
                .map(productInCart -> productInCart.getProduct().getPrice() * productInCart.getCount())
                .reduce(Double::sum)
                .orElse(0.0);
        int priceBeforePointForProducts = Integer.parseInt(String.valueOf(priceForProducts).split("\\.")[0]);
        int priceAfterPointForProducts = Integer.parseInt(String.valueOf(priceForProducts).split("\\.")[1]);

        cartDataDTO.setPriceBeforePointForProducts(priceBeforePointForProducts);
        cartDataDTO.setPriceAfterPointForProducts(priceAfterPointForProducts);

        double priceForDelivery = priceForProducts > 200 ? 0.0 : (8 + (0.5 * cart.getProductsInCart().size()));
        int priceBeforePointForDelivery = Integer.parseInt(String.valueOf(priceForDelivery).split("\\.")[0]);
        int priceAfterPointForDelivery = Integer.parseInt(String.valueOf(priceForDelivery).split("\\.")[1]);

        cartDataDTO.setPriceBeforePointForDelivery(priceBeforePointForDelivery);
        cartDataDTO.setPriceAfterForDelivery(priceAfterPointForDelivery);

        double sum = priceForProducts + priceForDelivery;
        int priceBeforePointForSum = Integer.parseInt(String.valueOf(sum).split("\\.")[0]);
        int priceAfterPointForSum = Integer.parseInt(String.valueOf(sum).split("\\.")[1]);

        cartDataDTO.setPriceBeforePointForSum(priceBeforePointForSum);
        cartDataDTO.setPriceAfterPointForSum(priceAfterPointForSum);

        return cartDataDTO;
    }

    private CartProductDTO toCartProductDTO(Product product) {
        CartProductDTO cartProductDTO = new CartProductDTO();

        cartProductDTO.setId(product.getId());
        cartProductDTO.setName(product.getName());
        cartProductDTO.setImageUrl(product.getMainImage().getImageUrl());

        int priceBeforePoint = Integer.parseInt(String.valueOf(product.getMainImage().getImageUrl()).split("\\.")[0]);
        cartProductDTO.setPriceBeforePoint(priceBeforePoint);
        int priceAfterPoint = Integer.parseInt(String.valueOf(product.getMainImage().getImageUrl()).split("\\.")[1]);
        cartProductDTO.setPriceAfterPoint(priceAfterPoint);

        return cartProductDTO;
    }
}
