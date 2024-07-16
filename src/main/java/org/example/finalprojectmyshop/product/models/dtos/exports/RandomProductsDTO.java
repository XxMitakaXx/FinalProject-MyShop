package org.example.finalprojectmyshop.product.models.dtos.exports;

public class RandomProductsDTO {
    private long id;
    private String name;
    private String imageUrl;
    private int priceBeforePoint;
    private int priceAfterPoint;
    private double rating;
    private int reviewsCount;

    public RandomProductsDTO() {}

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPriceBeforePoint() {
        return this.priceBeforePoint;
    }

    public void setPriceBeforePoint(int priceBeforePoint) {
        this.priceBeforePoint = priceBeforePoint;
    }

    public int getPriceAfterPoint() {
        return this.priceAfterPoint;
    }

    public void setPriceAfterPoint(int priceAfterPoint) {
        this.priceAfterPoint = priceAfterPoint;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReviewsCount() {
        return this.reviewsCount;
    }

    public void setReviewsCount(int reviewsCount) {
        this.reviewsCount = reviewsCount;
    }
}
