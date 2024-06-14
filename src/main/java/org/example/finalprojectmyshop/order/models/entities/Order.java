package org.example.finalprojectmyshop.order.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.address.models.entities.Address;
import org.example.finalprojectmyshop.order.models.entities.enums.OrderStatus;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.user.models.entities.User;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private Address address;

    @ManyToOne
    private User buyer;

    @ManyToMany
    private Set<Product> products;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "is_picked", nullable = false)
    private boolean isPicked;

    public Order() {}

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

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public boolean isPicked() {
        return this.isPicked;
    }

    public void setPicked(boolean picked) {
        isPicked = picked;
    }
}
