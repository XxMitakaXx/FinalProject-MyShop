package org.example.finalprojectmyshop.order.models.dtos.exports;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SaleInfoDTO {
    private long id;
    private Date date;
    private String buyerEmail;
    private Set<SaleInfoProductDTO> products;
    private double sum;
    private String cityVillage;
    private String address;

    public SaleInfoDTO() {
        this.products = new HashSet<>();
    }

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

    public String getBuyerEmail() {
        return this.buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public Set<SaleInfoProductDTO> getProducts() {
        return this.products;
    }

    public void setProducts(Set<SaleInfoProductDTO> products) {
        this.products = products;
    }

    public double getSum() {
        return this.sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
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
}
