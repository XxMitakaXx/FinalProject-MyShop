package org.example.finalprojectmyshop.product.models.dtos.exports;

public class ReviewDataDTO {
    private String title;
    private String text;
    private String postDate;
    private ReviewUserDataDTO userData;
    private double rating;

    public ReviewDataDTO() {}

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPostDate() {
        return this.postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public ReviewUserDataDTO getUserData() {
        return this.userData;
    }

    public void setUserData(ReviewUserDataDTO userData) {
        this.userData = userData;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
