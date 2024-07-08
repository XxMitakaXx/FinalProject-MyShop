package org.example.finalprojectmyshop.product.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "product-properties")
public class ProductProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String value;

//    @ManyToOne
//    private Product product;

    public ProductProperty() {}

    public long getId() {
        return this.id;
    }

    public ProductProperty setId(long id) {
        this.id = id;

        return this;
    }

    public String getName() {
        return this.name;
    }

    public ProductProperty setName(String name) {
        this.name = name;

        return this;
    }

    public String getValue() {
        return this.value;
    }

    public ProductProperty setValue(String value) {
        this.value = value;

        return this;
    }

//    public Product getProduct() {
//        return this.product;
//    }
//
//    public ProductProperty setProduct(Product product) {
//        this.product = product;
//
//        return this;
//    }
}
