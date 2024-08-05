package org.example.finalprojectmyshop.product.models.dtos.exports;

import java.util.Date;

public class ReviewDTO {
    private long id;
    private String title;
    private double rating;
    private String description;
    private Date date;
    private ReviewUserDTO user;
    private ReviewProductDTO product;

    public ReviewDTO() {}

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ReviewUserDTO getUser() {
        return this.user;
    }

    public void setUser(ReviewUserDTO user) {
        this.user = user;
    }

    public ReviewProductDTO getProduct() {
        return this.product;
    }

    public void setProduct(ReviewProductDTO product) {
        this.product = product;
    }
}
