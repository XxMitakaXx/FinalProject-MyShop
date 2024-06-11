package org.example.finalprojectmyshop.product.models.entities;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @Column(name = "properties")
    private HashMap<String, String> properties;

    @Column(name = "color")
    private String color;

//  TODO
//   private image

//  TODO
//   private category

//   TODO
//    private warranty

    @Column(name = "available")
    private boolean available;

    @Column(name = "ratings")
    private List<Double> ratings;

    @Column(name = "express_ship")
    private boolean expressShip;

    @Column(name = "leasing")
    private boolean leasing;

    public Product() {}

    public Product(String name, double price, String description, HashMap<String, String> properties, String color, boolean available, boolean expressShip, boolean leasing) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.properties = properties;
        this.color = color;
        this.available = available;
        this.expressShip = expressShip;
        this.leasing = leasing;
    }
}
