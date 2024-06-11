package org.example.finalprojectmyshop.promotion.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

//  TODO
//    private products

    @Column(name = "is_active")
    private boolean isActive;

    public Promotion() {}

    public Promotion(String title, boolean isActive) {
        this.title = title;
        this.isActive = isActive;
    }
}
