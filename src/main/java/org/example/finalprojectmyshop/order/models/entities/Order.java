package org.example.finalprojectmyshop.order.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.order.models.enums.CollectingPlace;
import org.example.finalprojectmyshop.order.models.enums.OrderLogisticStatus;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_and_last_name", nullable = false)
    private String firstAndLastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "city_village", nullable = false)
    private String cityVillage;

    @Column(name = "address", nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "collecting_place", nullable = false)
    private CollectingPlace collectingPlace;

    @Column(name = "price_for_products", nullable = false)
    private double priceForProducts;

    @Column(name = "price_for_delivery", nullable = false)
    private double priceForDelivery;

    @Column(name = "price_for_sum", nullable = false)
    private double priceForSum;

    @Column(name = "order-date", nullable = false)
    private Date orderDate;

    @ManyToOne
    private UserEntity buyer;

    @ManyToMany
    private Set<Product> products;

    @Enumerated(EnumType.STRING)
    @Column(name = "logistic_status")
    private OrderLogisticStatus logisticStatus;


    @Column(name = "is_picked", nullable = false)
    private boolean isPicked;

    public Order() {
        this.products = new HashSet<>();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(Date date) {
        this.orderDate = date;
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

    public void setLogisticStatus(OrderLogisticStatus logisticStatus) {
        this.logisticStatus = logisticStatus;
    }

    public boolean isPicked() {
        return this.logisticStatus == OrderLogisticStatus.RECEIVED;
    }

    public void setPicked(boolean picked) {
        isPicked = picked;
    }
}
