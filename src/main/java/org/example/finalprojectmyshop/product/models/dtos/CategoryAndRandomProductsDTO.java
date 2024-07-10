package org.example.finalprojectmyshop.product.models.dtos;

import org.example.finalprojectmyshop.product.models.entities.Product;

import java.util.HashSet;
import java.util.Set;

public class CategoryAndRandomProductsDTO {
    private long id;
    private String name;
    private Set<RandomProductsDTO> products;

    public CategoryAndRandomProductsDTO() {
        this.products = new HashSet<>();
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

    public Set<RandomProductsDTO> getProducts() {
        return this.products;
    }

    public void setProducts(Set<RandomProductsDTO> products) {
        this.products = products;
    }
}
