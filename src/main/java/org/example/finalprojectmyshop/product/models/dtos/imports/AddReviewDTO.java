package org.example.finalprojectmyshop.product.models.dtos.imports;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class AddReviewDTO {

    @Size(min = 2, max = 20)
    private String title;

    @Positive
    private Double rating;

    @Size(min = 10, max = 100)
    private String description;;

    public AddReviewDTO() {}

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRating() {
        return this.rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
