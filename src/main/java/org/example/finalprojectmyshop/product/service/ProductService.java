package org.example.finalprojectmyshop.product.service;

import org.example.finalprojectmyshop.product.models.dtos.AddProductDTO;
import org.example.finalprojectmyshop.product.models.dtos.ProductDetailsDTO;
import org.example.finalprojectmyshop.product.models.entities.Product;

public interface ProductService {
    void save(AddProductDTO addProductDTO);
    void addProductToFavorites(long id);
    ProductDetailsDTO findById(long id);
    Product findProductEntityById(long id);
}
