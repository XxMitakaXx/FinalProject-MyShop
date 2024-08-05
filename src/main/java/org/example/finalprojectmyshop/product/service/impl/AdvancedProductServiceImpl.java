package org.example.finalprojectmyshop.product.service.impl;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFileEntity;
import org.example.finalprojectmyshop.mediaFile.service.ImagesHelperService;
import org.example.finalprojectmyshop.order.models.entities.CartEntity;
import org.example.finalprojectmyshop.order.models.entities.ProductInCartEntity;
import org.example.finalprojectmyshop.order.service.CartService;
import org.example.finalprojectmyshop.order.service.ProductInCartService;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.product.models.entities.ProductProperty;
import org.example.finalprojectmyshop.product.models.entities.SecondaryCategory;
import org.example.finalprojectmyshop.product.service.*;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdvancedProductServiceImpl implements AdvancedProductService {
    private final ProductService productService;
    private final SecondaryCategoryService secondaryCategoryService;
    private final ImagesHelperService imagesHelperService;
    private final ReviewService reviewService;
    private final ProductPropertyService productPropertyService;
    private final UserService userService;
    private final ProductInCartService productInCartService;
    private final CartService cartService;

    public AdvancedProductServiceImpl(ProductService productService, SecondaryCategoryService secondaryCategoryService, ImagesHelperService imagesHelperService, ReviewService reviewService, ProductPropertyService productPropertyService, UserService userService, ProductInCartService productInCartService, CartService cartService) {
        this.productService = productService;
        this.secondaryCategoryService = secondaryCategoryService;
        this.imagesHelperService = imagesHelperService;
        this.reviewService = reviewService;
        this.productPropertyService = productPropertyService;
        this.userService = userService;
        this.productInCartService = productInCartService;
        this.cartService = cartService;
    }

    @Override
    public void deleteProduct(long id) {
        Product product = this.productService.findProductEntityById(id);

        SecondaryCategory secondaryCategory = this.secondaryCategoryService.findSecondaryCategoryEntityByName(product.getSecondaryCategory().getName());
        secondaryCategory.getProducts().remove(product);
        this.secondaryCategoryService.save(secondaryCategory);

        List<ProductProperty> productProperties = product.getProperties();
        productProperties.forEach(productProperty -> {
            product.getProperties().remove(productProperty);
            this.productService.save(product);
            this.productPropertyService.deleteProductProperty(productProperty.getId());
        });

        ArrayList<MediaFileEntity> list = new ArrayList<>();
        product.getImages().forEach(image -> list.add(image));
        for (MediaFileEntity image : list) {
            product.getImages().remove(image);
            this.imagesHelperService.deleteImage(image);
        }

        product.getReviews().forEach(this.reviewService::deleteReview);

        Set<UserEntity> users = this.userService.findAllUsers();

        users.forEach(user -> {
            boolean containInFavorites = user
                    .getFavorites()
                    .stream()
                    .map(Product::getId)
                    .anyMatch(productInFavoriteId -> productInFavoriteId.equals(product.getId()));

            if (containInFavorites) {
                user.getFavorites().remove(product);
                this.userService.save(user);
            }

            boolean containInCart = user
                    .getCart()
                    .getProductsInCart()
                    .stream()
                    .map(productInCartEntity -> productInCartEntity.getProduct().getId())
                    .anyMatch(productInCartId -> productInCartId == product.getId());

            if (containInCart) {
                CartEntity cart = user.getCart();
                Set<Long> productInCartIds = cart.getProductsInCart()
                        .stream()
                        .map(ProductInCartEntity::getId)
                        .collect(Collectors.toSet());

                CartEntity cartEntity = new CartEntity();
                this.cartService.save(cartEntity);

                user.setCart(cartEntity);
                this.cartService.save(cartEntity);

                cart.getProductsInCart().forEach(productInCartEntity -> cart.getProductsInCart().remove(productInCartEntity));

                this.cartService.deleteById(cart.getId());

                productInCartIds.forEach(this.productInCartService::deleteById);
            }
        });

        this.productService.deleteProductById(product.getId());
    }
}
