package org.example.finalprojectmyshop.product.service;

public interface ProductInCartCrudService {
    void addProductToCart(long id);
    void deleteProductFromCartByProduct(long id);
}
