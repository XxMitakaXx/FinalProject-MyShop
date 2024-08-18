package org.example.finalprojectmyshop.product.service;

import org.example.finalprojectmyshop.product.models.dtos.exports.FoundedProductsByNameDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.FoundedProductsForDeleteDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.ProductDetailsDTO;
import org.example.finalprojectmyshop.product.models.dtos.imports.AddProductDTO;
import org.example.finalprojectmyshop.product.models.entities.Product;

import java.io.IOException;

public interface ProductService {
    void save(AddProductDTO addProductDTO) throws IOException;
    void save(Product product);
    void deleteProductById(long id);
    ProductDetailsDTO findById(long id);
    Product findProductEntityById(long id);
    void decreaseProductQuantity(long productId, int quantity);
    FoundedProductsForDeleteDTO searchProductsForDelete(String string);
    FoundedProductsByNameDTO findProductsByName(String name);
}
