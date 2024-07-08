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

    @OneToMany
    private Set<ProductProperty> properties;

//    private String color;

    @OneToMany
    private Set<MediaFile> imagesUrls;

    @ManyToOne
    private SecondaryCategory secondaryCategory;

    @Column(nullable = false)
    private boolean available;

    @OneToMany
    private Set<Rating> ratings;

    @Column(name = "express_ship", nullable = false)
    private boolean expressShip;

    @Column(nullable = false)
    private boolean leasing;

    @Column(nullable = false)
    private int quantity;

    public Product() {
        this.properties = new HashSet<>();
        this.imagesUrls = new HashSet<>();
        this.ratings = new HashSet<>();
    }

    public long getId() {
        return this.id;
    }

    public Product setId(long id) {
        this.id = id;

        return this;
    }

    public String getName() {
        return this.name;
    }

    public Product setName(String name) {
        this.name = name;

        return this;
    }

    public double getPrice() {
        return this.price;
    }

    public Product setPrice(double price) {
        this.price = price;

        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public Product setDescription(String description) {
        this.description = description;

        return this;
    }

//    public String getColor() {
//        return this.color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }


    public Set<ProductProperty> getProperties() {
        return this.properties;
    }

    public Product setProperties(Set<ProductProperty> properties) {
        this.properties = properties;

        return this;
    }

    public Set<MediaFile> getImagesUrls() {
        return this.imagesUrls;
    }

    public Product setImagesUrls(Set<MediaFile> imagesUrls) {
        this.imagesUrls = imagesUrls;

        return this;
    }

    public SecondaryCategory getSecondaryCategory() {
        return this.secondaryCategory;
    }

    public Product setSecondaryCategory(SecondaryCategory secondaryCategory) {
        this.secondaryCategory = secondaryCategory;

        return this;
    }

    public boolean isAvailable() {
        return this.quantity > 0;
    }

    public Set<Rating> getRatings() {
        return this.ratings;
    }

    public Product setRatings(Set<Rating> ratings) {
        this.ratings = ratings;

        return this;
    }

    public boolean isExpressShip() {
        return this.expressShip;
    }

    public Product setExpressShip(boolean expressShip) {
        this.expressShip = expressShip;

        return this;
    }

    public boolean isLeasing() {
        return this.leasing;
    }

    public Product setLeasing(boolean leasing) {
        this.leasing = leasing;

        return this;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public Product setQuantity(int quantity) {
        this.quantity = quantity;

        return this;
    }
}
