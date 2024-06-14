package org.example.finalprojectmyshop.sale.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.address.models.entities.Address;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.user.models.entities.User;

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

    private String description;

    @ManyToOne
    private User buyer;

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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getBuyer() {
        return this.buyer;
    }

    public void setBuyer(User buyer) {
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
