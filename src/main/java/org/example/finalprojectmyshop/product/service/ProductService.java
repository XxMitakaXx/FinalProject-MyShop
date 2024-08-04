package org.example.finalprojectmyshop.product.service;

import org.example.finalprojectmyshop.product.models.dtos.exports.FavoriteProductDTO;
import org.example.finalprojectmyshop.product.models.dtos.imports.AddProductDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.ProductDetailsDTO;
import org.example.finalprojectmyshop.product.models.entities.Product;

import java.io.IOException;
import java.util.Set;

public interface ProductService {
    void save(AddProductDTO addProductDTO) throws IOException;
    void addProductToFavorites(long id);
    void deleteProductFromFavorites(long id);
    void addProductToCart(long id);
    void deleteProductFromCart(long id);
    ProductDetailsDTO findById(long id);
    Product findProductEntityById(long id);
    Set<FavoriteProductDTO> findFavoriteProducts();
}
