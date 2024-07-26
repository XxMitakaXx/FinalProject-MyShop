package org.example.finalprojectmyshop.order.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.product.models.entities.Product;

@Entity
@Table(name = "products_in_carts")
public class ProductInCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Product product;

    private int count;

    public ProductInCartEntity() {}

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
