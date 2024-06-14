package org.example.finalprojectmyshop.product.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.category.models.entities.Category;
import org.example.finalprojectmyshop.rating.models.entities.Rating;

import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "description", nullable = false)
    private String description;

//    private HashMap<String, String> properties;

    private String color;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    private Category category;

    @Column(name = "available", nullable = false)
    private boolean available;

    @OneToMany
    private Set<Rating> ratings;

    @Column(name = "express_ship", nullable = false)
    private boolean expressShip;

    @Column(name = "leasing", nullable = false)
    private boolean leasing;

    public Product() {}


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

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Set<Rating> getRatings() {
        return this.ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public boolean isExpressShip() {
        return this.expressShip;
    }

    public void setExpressShip(boolean expressShip) {
        this.expressShip = expressShip;
    }

    public boolean isLeasing() {
        return this.leasing;
    }

    public void setLeasing(boolean leasing) {
        this.leasing = leasing;
    }
}
