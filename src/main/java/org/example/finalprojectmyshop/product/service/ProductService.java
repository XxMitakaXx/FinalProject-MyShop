package org.example.finalprojectmyshop.product.service;

import org.example.finalprojectmyshop.product.models.dtos.imports.AddProductDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.ProductDetailsDTO;
import org.example.finalprojectmyshop.product.models.entities.Product;

public interface ProductService {
    void save(AddProductDTO addProductDTO);
    void addProductToFavorites(long id);
    ProductDetailsDTO findById(long id);
    Product findProductEntityById(long id);
}
