package org.example.finalprojectmyshop.order.models.dtos.exports;

import java.util.HashSet;
import java.util.Set;

public class CartDataDTO {
    Set<CartProductDTO> productsInCart;
    private double priceForProducts;
    private double priceForDelivery;
    private double priceForSum;

    public CartDataDTO() {
        this.productsInCart = new HashSet<>();
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
