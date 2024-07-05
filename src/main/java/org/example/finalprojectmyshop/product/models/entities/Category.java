package org.example.finalprojectmyshop.product.models.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<SecondaryCategory> secondaryCategories;

    public Category() {
        this.secondaryCategories = new HashSet<>();
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

    public Set<SecondaryCategory> getSecondaryCategories() {
        return this.secondaryCategories;
    }

    public void setSecondaryCategories(Set<SecondaryCategory> secondaryCategories) {
        this.secondaryCategories = secondaryCategories;
    }
}
