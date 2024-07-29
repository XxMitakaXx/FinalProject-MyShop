package org.example.finalprojectmyshop.order.models.dtos.exports;

import org.example.finalprojectmyshop.product.models.entities.Product;

public class OrderDetailsProductDTO {
    private Product product;
    private int count;

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
