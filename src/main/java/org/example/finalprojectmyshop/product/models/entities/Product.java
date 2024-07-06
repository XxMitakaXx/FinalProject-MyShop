package org.example.finalprojectmyshop.product.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFile;

import java.util.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "product")
    private Set<ProductProperty> properties;

//    private String color;

    @OneToMany
    private Set<MediaFile> imagesUrls;

    @ManyToOne
    private Category category;

    @Column(nullable = false)
    private boolean available;

    @OneToMany
    private Set<Rating> ratings;

    @Column(name = "express_ship", nullable = false)
    private boolean expressShip;

    @Column(nullable = false)
    private boolean leasing;

    public Product() {
        this.imagesUrls = new HashSet<>();
        this.ratings = new HashSet<>();
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

//    public String getColor() {
//        return this.color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }

    public Set<MediaFile> getImagesUrls() {
        return this.imagesUrls;
    }

    public void setImagesUrls(Set<MediaFile> imagesUrls) {
        this.imagesUrls = imagesUrls;
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
