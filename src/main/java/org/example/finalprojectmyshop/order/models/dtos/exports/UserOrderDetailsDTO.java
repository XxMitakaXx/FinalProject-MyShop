package org.example.finalprojectmyshop.order.models.dtos.exports;

import org.example.finalprojectmyshop.order.models.enums.CollectingPlace;
import org.example.finalprojectmyshop.order.models.enums.OrderLogisticStatus;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserOrderDetailsDTO {
    private String firstAndLastName;
    private String phoneNumber;
    private String cityVillage;
    private String address;
    private CollectingPlace collectingPlace;
    private double priceForProducts;
    private double priceForDelivery;
    private double priceForSum;
    private Date orderDate;
    private Date deliveryDate;
    private Set<OrderDetailsProductDTO> products;
    private OrderLogisticStatus orderLogisticStatus;

    public UserOrderDetailsDTO() {
        this.products = new HashSet<>();
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

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Set<OrderDetailsProductDTO> getProducts() {
        return this.products;
    }

    public void setProducts(Set<OrderDetailsProductDTO> products) {
        this.products = products;
    }

    public OrderLogisticStatus getOrderLogisticStatus() {
        return this.orderLogisticStatus;
    }

    public void setOrderLogisticStatus(OrderLogisticStatus orderLogisticStatus) {
        this.orderLogisticStatus = orderLogisticStatus;
    }
}
