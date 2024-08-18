package org.example.finalprojectmyshop.product.service;

import org.example.finalprojectmyshop.product.models.dtos.exports.FavoriteProductDTO;

import java.util.Set;

public interface FavoriteProductService {
    void addProductToFavorites(long id);
    void deleteProductFromFavorites(long id);
    Set<FavoriteProductDTO> findFavoriteProducts();
}
