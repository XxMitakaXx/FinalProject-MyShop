package org.example.finalprojectmyshop.order.models.dtos.exports;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.order.models.enums.CollectingPlace;
import org.example.finalprojectmyshop.order.models.enums.OrderLogisticStatus;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserOrderDTO {
    private long id;
    private String firstAndLastName;
    private String phoneNumber;
    private String cityVillage;
    private String address;
    private CollectingPlace collectingPlace;
    private double priceForProducts;
    private double priceForDelivery;
    //private double priceForSum;
    //private Date date;
    private UserEntity buyer;
    private Set<Product> products;
    //private OrderLogisticStatus logisticStatus;
    private boolean isPicked;

    public UserOrderDTO() {
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
