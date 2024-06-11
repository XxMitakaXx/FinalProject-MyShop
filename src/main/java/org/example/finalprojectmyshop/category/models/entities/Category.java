package org.example.finalprojectmyshop.category.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

//  TODO
//   private products

    public Category() {}

    public Category(String name) {
        this.name = name;
    }


}
