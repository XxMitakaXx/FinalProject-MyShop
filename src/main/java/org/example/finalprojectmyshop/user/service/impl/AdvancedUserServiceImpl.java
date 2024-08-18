package org.example.finalprojectmyshop.user.service.impl;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFileEntity;
import org.example.finalprojectmyshop.mediaFile.service.ImagesHelperService;
import org.example.finalprojectmyshop.order.models.entities.Address;
import org.example.finalprojectmyshop.order.models.entities.CartEntity;
import org.example.finalprojectmyshop.order.models.entities.Order;
import org.example.finalprojectmyshop.order.models.entities.ProductInCartEntity;
import org.example.finalprojectmyshop.order.service.AddressService;
import org.example.finalprojectmyshop.order.service.CartService;
import org.example.finalprojectmyshop.order.service.OrderCrudService;
import org.example.finalprojectmyshop.order.service.ProductInCartService;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.product.models.entities.Warranty;
import org.example.finalprojectmyshop.product.service.ReviewService;
import org.example.finalprojectmyshop.product.service.WarrantyService;
import org.example.finalprojectmyshop.user.models.dtos.exports.FoundedUserForDeleteDTO;
import org.example.finalprojectmyshop.user.models.dtos.exports.FoundedUserForEditDTO;
import org.example.finalprojectmyshop.user.models.dtos.exports.FoundedUsersForDeleteDTO;
import org.example.finalprojectmyshop.user.models.dtos.exports.FoundedUsersForEditDTO;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.models.entities.UserRoleEntity;
import org.example.finalprojectmyshop.user.service.AdvancedUserService;
import org.example.finalprojectmyshop.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdvancedUserServiceImpl implements AdvancedUserService {

    private final UserHelperService userHelperService;
    private final UserService userService;
    private final AddressService addressService;
    private final CartService cartService;
    private final OrderCrudService orderCrudService;
    private final ReviewService reviewService;
    private final WarrantyService warrantyService;
    private final ProductInCartService productInCartService;
    private final ImagesHelperService imagesHelperService;

    public AdvancedUserServiceImpl(UserHelperService userHelperService, UserService userService, AddressService addressService, CartService cartService, OrderCrudService orderCrudService, ReviewService reviewService, WarrantyService warrantyService, ProductInCartService productInCartService, ImagesHelperService imagesHelperService) {
        this.userHelperService = userHelperService;
        this.userService = userService;
        this.addressService = addressService;
        this.cartService = cartService;
        this.orderCrudService = orderCrudService;
        this.reviewService = reviewService;
        this.warrantyService = warrantyService;
        this.productInCartService = productInCartService;
        this.imagesHelperService = imagesHelperService;
    }

    @Override
    public FoundedUsersForDeleteDTO findUsersForDelete(String email) {
        List<UserEntity> foundedUserEntities = this.userService.findUsersByEmail(email);

        FoundedUsersForDeleteDTO foundedUsersForDeleteDTO = new FoundedUsersForDeleteDTO();

        foundedUserEntities.forEach(user -> {
            FoundedUserForDeleteDTO foundedUserForDeleteDTO = toFoundedUserForDeleteDTO(user);

            if (!(foundedUserForDeleteDTO.getId() == this.userHelperService.getUser().getId())) {
                foundedUsersForDeleteDTO.getFoundedUsersForDeleteDTO().add(foundedUserForDeleteDTO);
            }
        });

        return foundedUsersForDeleteDTO;
    }

    @Override
    public FoundedUsersForEditDTO findUsersForEdit(String email) {
        List<UserEntity> foundedUserEntities = this.userService.findUsersByEmail(email);

        FoundedUsersForEditDTO foundedUsersForEditDTO = new FoundedUsersForEditDTO();

        foundedUserEntities.forEach(user -> {
            FoundedUserForEditDTO foundedUserForEditDTO = toFoundedUserForEditDTO(user);

            if (!(foundedUserForEditDTO.getId() == this.userHelperService.getUser().getId())) {
                foundedUsersForEditDTO.getFoundedUsersForEditDTOS().add(foundedUserForEditDTO);
            }
        });

        return foundedUsersForEditDTO;
    }

    @Override
    public void deleteUser(long id) {
        UserEntity userEntity = this.userService.findUserEntity(id);

        Set<Address> addresses = new HashSet<>();
        userEntity.getAddresses().forEach(address -> addresses.add(address));
        addresses.forEach(address -> {
            userEntity.getAddresses().remove(address);
            this.userService.save(userEntity);
            this.addressService.deleteAddress(address.getId());
        });

        this.deleteUserCart(userEntity);

        Set<Product> favorites = new HashSet<>();
        userEntity.getFavorites().forEach(favorite -> favorites.add(favorite));
        favorites.forEach(favorite -> {
            userEntity.getFavorites().remove(favorite);
        });
        this.userService.save(userEntity);

        Set<Order> orders = new HashSet<>();
        userEntity.getOrders().forEach(order -> orders.add(order));
        orders.forEach(order -> {
            userEntity.getOrders().remove(order);
            this.userService.save(userEntity);
            this.orderCrudService.deleteOrder(order.getId());
        });

        userEntity.getReviews().forEach(this.reviewService::deleteReview);

        Set<Warranty> warranties = new HashSet<>();
        userEntity.getWarranties().forEach(warranty -> warranties.add(warranty));
        warranties.forEach(warranty -> {
            userEntity.getWarranties().remove(warranty);
            this.userService.save(userEntity);
            this.warrantyService.deleteWarranty(warranty.getId());
        });

        Set<UserRoleEntity> userRoleEntities = new HashSet<>();
        userEntity.getRoles().forEach(userRoleEntity -> userRoleEntities.add(userRoleEntity));
        userRoleEntities.forEach(userRoleEntity -> {
            userEntity.getRoles().remove(userRoleEntity);
        });

        this.userService.save(userEntity);

        MediaFileEntity profilePicture = userEntity.getProfilePicture();
        userEntity.setProfilePicture(null);
        this.userService.save(userEntity);
        this.imagesHelperService.deleteImage(profilePicture);

        this.userService.deleteUserEntity(userEntity.getId());
    }

    private void deleteUserCart(UserEntity user) {
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
        this.cartService.save(cart);

        user.setCart(null);
        this.userService.save(user);

        this.cartService.deleteById(cart.getId());

        productInCartEntitiesIds.forEach(this.productInCartService::deleteById);
    }


    private FoundedUserForEditDTO toFoundedUserForEditDTO(UserEntity user) {
        FoundedUserForEditDTO foundedUserForEditDTO = new FoundedUserForEditDTO();

        foundedUserForEditDTO.setId(user.getId());
        foundedUserForEditDTO.setFirstName(user.getFirstName());
        foundedUserForEditDTO.setLastName(user.getLastName());
        foundedUserForEditDTO.setProfilePictureUrl(user.getProfilePicture().getImageUrl());

        return foundedUserForEditDTO;
    }

    private FoundedUserForDeleteDTO toFoundedUserForDeleteDTO(UserEntity user) {
        FoundedUserForDeleteDTO foundedUserForDeleteDTO = new FoundedUserForDeleteDTO();

        foundedUserForDeleteDTO.setId(user.getId());
        foundedUserForDeleteDTO.setFirstName(user.getFirstName());
        foundedUserForDeleteDTO.setLastName(user.getLastName());
        foundedUserForDeleteDTO.setProfilePictureUrl(user.getProfilePicture().getImageUrl());

        return foundedUserForDeleteDTO;
    }
}
