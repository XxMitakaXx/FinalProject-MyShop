package org.example.finalprojectmyshop.product.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFileEntity;

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

    @Column(name = "discount_price")
    private double discountPrice;

    @OneToMany
    private List<ProductProperty> properties;

    @OneToOne
    private MediaFileEntity mainImage;

    @OneToMany
    private Set<MediaFileEntity> images;

    @ManyToOne
    private SecondaryCategory secondaryCategory;

    @Column(nullable = false)
    private boolean available;

    @OneToMany(mappedBy = "product")
    private Set<Review> reviews;

    @Column(name = "express_ship", nullable = false)
    private boolean expressShip;

    @Column(nullable = false)
    private boolean leasing;

    @Column(nullable = false)
    private int quantity;

    public Product() {
        this.properties = new ArrayList<>();
        this.images = new HashSet<>();
        this.reviews = new HashSet<>();
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

    public double getDiscountPrice() {
        return this.discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }



    public void setAvailable(boolean available) {
        this.available = available;
    }

//    public String getColor() {
//        return this.color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }


    public List<ProductProperty> getProperties() {
        return this.properties;
    }

    public Product setProperties(List<ProductProperty> properties) {
        this.properties = properties;

        return this;
    }

    public MediaFileEntity getMainImage() {
        return this.mainImage;
    }

    public void setMainImage(MediaFileEntity mainImage) {
        this.mainImage = mainImage;
    }

    public Set<MediaFileEntity> getImages() {
        return this.images;
    }

    public Product setImages(Set<MediaFileEntity> imagesUrls) {
        this.images = imagesUrls;

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
        boolean isAvailable = this.quantity > 0;

        this.setAvailable(available);

        return isAvailable;
    }

    public Set<Review> getReviews() {
        return this.reviews;
    }

    public Product setReviews(Set<Review> ratings) {
        this.reviews = ratings;

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
