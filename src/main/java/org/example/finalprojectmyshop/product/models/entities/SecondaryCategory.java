package org.example.finalprojectmyshop.product.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.product.models.enums.SecondaryCategoryName;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "secondary_categories")
public class SecondaryCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private SecondaryCategoryName name;

    @ManyToOne
    private Category category;

    @OneToMany
    private Set<Product> products;

    public SecondaryCategory() {
        this.products = new HashSet<>();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SecondaryCategoryName getName() {
        return this.name;
    }

    public void setName(SecondaryCategoryName name) {
        this.name = name;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
