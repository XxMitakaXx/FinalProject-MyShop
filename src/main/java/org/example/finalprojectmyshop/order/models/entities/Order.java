package org.example.finalprojectmyshop.order.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.order.models.enums.OrderLogisticStatus;
import org.example.finalprojectmyshop.order.models.enums.OrderPaymentStatus;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;

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
    private UserEntity buyer;

    @ManyToMany
    private Set<Product> products;

    @Enumerated(EnumType.STRING)
    private OrderLogisticStatus logisticStatus;

    @Enumerated(EnumType.STRING)
    private OrderPaymentStatus paymentStatus;

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

    public OrderLogisticStatus getLogisticStatus() {
        return this.logisticStatus;
    }

    public void setLogisticStatus(OrderLogisticStatus status) {
        this.logisticStatus = status;
    }

    public OrderPaymentStatus getPaymentStatus() {
        return this.paymentStatus;
    }

    public void setPaymentStatus(OrderPaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean isPicked() {
        return this.isPicked;
    }

    public void setPicked(boolean picked) {
        isPicked = picked;
    }
}
