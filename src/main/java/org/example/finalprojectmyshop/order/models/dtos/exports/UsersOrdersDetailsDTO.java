package org.example.finalprojectmyshop.order.models.dtos.exports;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import org.example.finalprojectmyshop.order.models.enums.CollectingPlace;

import java.util.HashSet;
import java.util.Set;

public class UsersOrdersDetailsDTO {

    @NotNull
    @Positive
    private long id;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String firstAndLastName;

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 10)
    private String phoneNumber;

    @NotNull
    @NotEmpty
    private String cityVillage;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String address;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CollectingPlace collectingPlace;

    private Set<CartProductDTO> productsInCart;

    @NotNull
    @PositiveOrZero
    private double priceForProducts;

    @NotNull
    @PositiveOrZero
    private double priceForDelivery;

    @NotNull
    @PositiveOrZero
    private double priceForSum;

    public UsersOrdersDetailsDTO() {
        this.productsInCart = new HashSet<>();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstAndLastName() {
        return this.firstAndLastName;
    }

    public void setFirstAndLastName(String firstAndLastName) {
        this.firstAndLastName = firstAndLastName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCityVillage() {
        return this.cityVillage;
    }

    public void setCityVillage(String cityVillage) {
        this.cityVillage = cityVillage;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CollectingPlace getCollectingPlace() {
        return this.collectingPlace;
    }

    public void setCollectingPlace(CollectingPlace collectingPlace) {
        this.collectingPlace = collectingPlace;
    }

    public Set<CartProductDTO> getProductsInCart() {
        return this.productsInCart;
    }

    public void setProductsInCart(Set<CartProductDTO> productsInCart) {
        this.productsInCart = productsInCart;
    }

    public double getPriceForProducts() {
        return this.priceForProducts;
    }

    public void setPriceForProducts(double priceForProducts) {
        this.priceForProducts = priceForProducts;
    }

    public double getPriceForDelivery() {
        return this.priceForDelivery;
    }

    public void setPriceForDelivery(double priceForDelivery) {
        this.priceForDelivery = priceForDelivery;
    }

    public double getPriceForSum() {
        return this.priceForSum;
    }

    public void setPriceForSum(double priceForSum) {
        this.priceForSum = priceForSum;
    }
}
