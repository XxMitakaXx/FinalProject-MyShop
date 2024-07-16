package org.example.finalprojectmyshop.product.models.dtos.exports;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDetailsDTO {
    private long id;
    private String name;
    private String mainImageUrl;
    private List<String> imagesUrls;
    private double originalPrice;
    private double discountPrice;
    private Set<ReviewDataDTO> reviews;
    private double rating;
    private int reviewsCount;
    private int fiveStarsReviewsCount;
    private int fourStarsReviewsCount;
    private int threeStarsReviewsCount;
    private int twoStarsReviewsCount;
    private int oneStarsReviewsCount;
    private Set<ProductDetailsPropertyDTO> properties;
    private boolean isAvailable;

    public ProductDetailsDTO() {
        this.imagesUrls = new ArrayList<>();
        this.reviews = new HashSet<>();
        this.properties = new HashSet<>();
    }

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

    public String getMainImageUrl() {
        return this.mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public List<String> getImagesUrls() {
        return this.imagesUrls;
    }

    public void setImagesUrls(List<String> imagesUrls) {
        this.imagesUrls = imagesUrls;
    }

    public double getOriginalPrice() {
        return this.originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getDiscountPrice() {
        return this.discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Set<ReviewDataDTO> getReviews() {
        return this.reviews;
    }

    public void setReviews(Set<ReviewDataDTO> reviews) {
        this.reviews = reviews;
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

    public int getFiveStarsReviewsCount() {
        return this.fiveStarsReviewsCount;
    }

    public void setFiveStarsReviewsCount(int fiveStarsReviewsCount) {
        this.fiveStarsReviewsCount = fiveStarsReviewsCount;
    }

    public int getFourStarsReviewsCount() {
        return this.fourStarsReviewsCount;
    }

    public void setFourStarsReviewsCount(int fourStarsReviewsCount) {
        this.fourStarsReviewsCount = fourStarsReviewsCount;
    }

    public int getThreeStarsReviewsCount() {
        return this.threeStarsReviewsCount;
    }

    public void setThreeStarsReviewsCount(int threeStarsReviewsCount) {
        this.threeStarsReviewsCount = threeStarsReviewsCount;
    }

    public int getTwoStarsReviewsCount() {
        return this.twoStarsReviewsCount;
    }

    public void setTwoStarsReviewsCount(int twoStarsReviewsCount) {
        this.twoStarsReviewsCount = twoStarsReviewsCount;
    }

    public int getOneStarsReviewsCount() {
        return this.oneStarsReviewsCount;
    }

    public void setOneStarsReviewsCount(int oneStarsReviewsCount) {
        this.oneStarsReviewsCount = oneStarsReviewsCount;
    }

    public Set<ProductDetailsPropertyDTO> getProperties() {
        return this.properties;
    }

    public void setProperties(Set<ProductDetailsPropertyDTO> properties) {
        this.properties = properties;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
