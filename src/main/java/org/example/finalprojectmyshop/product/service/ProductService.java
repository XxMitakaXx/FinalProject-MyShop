package org.example.finalprojectmyshop.product.service;

import org.example.finalprojectmyshop.product.models.dtos.exports.FavoriteProductDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.FoundedProductsByNameDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.FoundedProductsForDeleteDTO;
import org.example.finalprojectmyshop.product.models.dtos.imports.AddProductDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.ProductDetailsDTO;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.product.models.enums.CategoryName;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ProductService {
    void save(AddProductDTO addProductDTO) throws IOException;
    void save(Product product);
    void deleteProductById(long id);
    void addProductToFavorites(long id);
    void deleteProductFromFavorites(long id);
    void addProductToCart(long id);
    void deleteProductFromCartByProduct(long id);
    ProductDetailsDTO findById(long id);
    Product findProductEntityById(long id);
    Set<FavoriteProductDTO> findFavoriteProducts();
    void decreaseProductQuantity(long productId, int quantity);
    FoundedProductsForDeleteDTO searchProductsForDelete(String string);
    FoundedProductsByNameDTO findProductsByName(String name);
}
