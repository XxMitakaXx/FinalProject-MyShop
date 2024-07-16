package org.example.finalprojectmyshop.product.models.dtos.exports;

import java.util.HashSet;
import java.util.Set;

public class ProductDetailsSecondaryCategoryDTO {
    private Set<RandomProductsDTO> products;

    public ProductDetailsSecondaryCategoryDTO() {
        this.products = new HashSet<>();
    }

    public Set<RandomProductsDTO> getProducts() {
        return this.products;
    }

    public void setProducts(Set<RandomProductsDTO> products) {
        this.products = products;
    }
}
