package org.example.finalprojectmyshop.order.service.impl;

import org.example.finalprojectmyshop.order.models.dtos.exports.CartDataDTO;
import org.example.finalprojectmyshop.order.models.dtos.exports.CartProductDTO;
import org.example.finalprojectmyshop.order.models.entities.CartEntity;
import org.example.finalprojectmyshop.order.models.entities.ProductInCartEntity;
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
    public void deleteById(long id) {
        this.cartRepository.deleteById(id);
    }

    @Override
    public CartDataDTO loadData() {
        CartDataDTO cartDataDTO = new CartDataDTO();

        CartEntity cart = this.userHelperService.getUser().getCart();

        Set<CartProductDTO> cartProductDTOs = cart.getProductsInCart()
                .stream()
                .map(productInCartEntity -> this.toCartProductDTO(productInCartEntity.getProduct(), productInCartEntity))
                .collect(Collectors.toSet());


        cartDataDTO.setProductsInCart(cartProductDTOs);

        double priceForProducts = cart.getProductsInCart()
                .stream()
                .map(productInCart -> productInCart.getProduct().getPrice() * productInCart.getCount())
                .reduce(Double::sum)
                .orElse(0.0);
        cartDataDTO.setPriceForProducts(priceForProducts);


        double priceForDelivery = priceForProducts > 200 ? 0.0 : (0.5 * cart.getProductsInCart().size());
        cartDataDTO.setPriceForDelivery(priceForDelivery);

        double sum = priceForProducts + priceForDelivery;
        cartDataDTO.setPriceForSum(sum);

        return cartDataDTO;
    }

    private CartProductDTO toCartProductDTO(Product product, ProductInCartEntity productInCartEntity) {
        CartProductDTO cartProductDTO = new CartProductDTO();

        cartProductDTO.setId(productInCartEntity.getId());
        cartProductDTO.setName(product.getName());
        cartProductDTO.setImageUrl(product.getMainImage().getImageUrl());
        cartProductDTO.setPrice(product.getPrice());
        cartProductDTO.setCount(productInCartEntity.getCount());
        cartProductDTO.setProductId(product.getId());

        return cartProductDTO;
    }
}
