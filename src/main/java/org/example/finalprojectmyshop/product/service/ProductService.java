package org.example.finalprojectmyshop.product.service;

import org.example.finalprojectmyshop.product.models.dtos.imports.AddProductDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.ProductDetailsDTO;
import org.example.finalprojectmyshop.product.models.entities.Product;

import java.io.IOException;

public interface ProductService {
    void save(AddProductDTO addProductDTO) throws IOException;
    void addProductToFavorites(long id);
    void addProductToCart(long id);
    ProductDetailsDTO findById(long id);
    Product findProductEntityById(long id);
}
