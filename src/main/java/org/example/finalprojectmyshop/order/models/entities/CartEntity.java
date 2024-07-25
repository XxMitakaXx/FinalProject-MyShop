package org.example.finalprojectmyshop.order.models.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private Set<ProductInCartEntity> productsInCart;

    public CartEntity() {
        this.productsInCart = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<ProductInCartEntity> getProductsInCart() {
        return this.productsInCart;
    }

    public void setProductsInCart(Set<ProductInCartEntity> productInCart) {
        this.productsInCart = productInCart;
    }
}
