package org.example.finalprojectmyshop.product.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.product.models.enums.CategoryName;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private CategoryName name;

    @OneToMany(mappedBy = "category")
    private Set<SecondaryCategory> secondaryCategories;

    public Category() {
        this.secondaryCategories = new HashSet<>();
    }


    public long getId() {
        return this.id;
    }

    public Category setId(long id) {
        this.id = id;

        return this;
    }

    public CategoryName getName() {
        return this.name;
    }

    public Category setName(CategoryName name) {
        this.name = name;

        return this;
    }

    public Set<SecondaryCategory> getSecondaryCategories() {
        return this.secondaryCategories;
    }

    public Category setSecondaryCategories(Set<SecondaryCategory> secondaryCategories) {
        this.secondaryCategories = secondaryCategories;

        return this;
    }
}
