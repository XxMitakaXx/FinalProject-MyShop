package org.example.finalprojectmyshop.order.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    private UserEntity buyer;

    @ManyToMany
    private Set<Product> products;

    @ManyToOne
    private Address address;

    public Sale() {}

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserEntity getBuyer() {
        return this.buyer;
    }

    public void setBuyer(UserEntity buyer) {
        this.buyer = buyer;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
