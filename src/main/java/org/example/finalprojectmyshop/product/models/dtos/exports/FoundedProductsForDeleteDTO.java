package org.example.finalprojectmyshop.product.models.dtos.exports;

import java.util.HashSet;
import java.util.Set;

public class FoundedProductsForDeleteDTO {
    Set<FoundedProductForDeleteDTO> foundedProducts;

    public FoundedProductsForDeleteDTO() {
        this.foundedProducts = new HashSet<>();
    }

    public Set<FoundedProductForDeleteDTO> getFoundedProducts() {
        return this.foundedProducts;
    }

    public void setFoundedProducts(Set<FoundedProductForDeleteDTO> foundedProducts) {
        this.foundedProducts = foundedProducts;
    }
}
