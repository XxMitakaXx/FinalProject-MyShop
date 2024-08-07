package org.example.finalprojectmyshop.order.models.dtos.imports;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.example.finalprojectmyshop.order.models.dtos.exports.CartProductDTO;
import org.example.finalprojectmyshop.order.models.enums.CollectingPlace;

import java.util.HashSet;
import java.util.Set;

public class OrderDetailsDTO {

    @NotEmpty(message = "Enter your names!")
    @Size(min = 2, max = 50, message = "The entered names must be with length of 2 to 50!")
    private String firstAndLastName;

    @NotEmpty(message = "Enter your phone number!")
    @Size(min = 10, max = 10, message = "Invalid phone number with valid length!")
    private String phoneNumber;

    @NotEmpty(message = "Enter your city/village!")
    private String cityVillage;

    @NotEmpty(message = "Enter address for delivery!")
    @Size(min = 2, max = 50, message = "Address name length must be between 2 and 50!")
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

    public OrderDetailsDTO() {
        this.productsInCart = new HashSet<>();
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
