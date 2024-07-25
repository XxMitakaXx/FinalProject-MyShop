package org.example.finalprojectmyshop.order.models.dtos.exports;

import org.example.finalprojectmyshop.order.models.entities.ProductInCartEntity;

import java.util.HashSet;
import java.util.Set;

public class CartDataDTO {
    Set<CartProductDTO> productsInCart;
    private int priceBeforePointForProducts;
    private int priceAfterPointForProducts;
    private int priceBeforePointForDelivery;
    private int priceAfterForDelivery;
    private int priceBeforePointForSum;
    private int priceAfterPointForSum;

    public CartDataDTO() {
        this.productsInCart = new HashSet<>();
    }

    public Set<CartProductDTO> getProductsInCart() {
        return this.productsInCart;
    }

    public void setProductsInCart(Set<CartProductDTO> productsInCart) {
        this.productsInCart = productsInCart;
    }

    public double getPriceBeforePointForProducts() {
        return this.priceBeforePointForProducts;
    }

    public void setPriceBeforePointForProducts(int priceBeforePointForProducts) {
        this.priceBeforePointForProducts = priceBeforePointForProducts;
    }

    public double getPriceAfterPointForProducts() {
        return this.priceAfterPointForProducts;
    }

    public void setPriceAfterPointForProducts(int priceAfterPointForProducts) {
        this.priceAfterPointForProducts = priceAfterPointForProducts;
    }

    public int getPriceBeforePointForDelivery() {
        return this.priceBeforePointForDelivery;
    }

    public void setPriceBeforePointForDelivery(int priceBeforePointForDelivery) {
        this.priceBeforePointForDelivery = priceBeforePointForDelivery;
    }

    public int getPriceAfterForDelivery() {
        return this.priceAfterForDelivery;
    }

    public void setPriceAfterForDelivery(int priceAfterForDelivery) {
        this.priceAfterForDelivery = priceAfterForDelivery;
    }

    public int getPriceBeforePointForSum() {
        return this.priceBeforePointForSum;
    }

    public void setPriceBeforePointForSum(int priceBeforePointForSum) {
        this.priceBeforePointForSum = priceBeforePointForSum;
    }

    public int getPriceAfterPointForSum() {
        return this.priceAfterPointForSum;
    }

    public void setPriceAfterPointForSum(int priceAfterPointForSum) {
        this.priceAfterPointForSum = priceAfterPointForSum;
    }
}
