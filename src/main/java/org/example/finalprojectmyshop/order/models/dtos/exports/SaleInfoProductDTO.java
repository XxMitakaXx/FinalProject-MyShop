package org.example.finalprojectmyshop.order.models.dtos.exports;

public class SaleInfoProductDTO {
    private String name;
    private double price;
    private String imageUrl;

    public SaleInfoProductDTO() {}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
