package org.example.finalprojectmyshop.product.models.dtos;

public class ProductDetailReviewDTO {
    private double rating;
    private ReviewUserDataDTO user;

    public ProductDetailReviewDTO() {}

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public ReviewUserDataDTO getUser() {
        return this.user;
    }

    public void setUser(ReviewUserDataDTO user) {
        this.user = user;
    }
}
