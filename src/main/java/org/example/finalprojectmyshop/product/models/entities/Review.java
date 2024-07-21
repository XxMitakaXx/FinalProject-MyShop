package org.example.finalprojectmyshop.product.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;

import java.util.Date;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @OneToOne
    private Rating rating;

    private String description;

    private Date date;

    @ManyToOne
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    public Review() {}

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

    public Rating getRating() {
        return this.rating;
    }

    public void setRating(Rating rating) {
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

    public UserEntity getUser() {
        return this.userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
