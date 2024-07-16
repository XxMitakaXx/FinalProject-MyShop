package org.example.finalprojectmyshop.product.service;

import org.example.finalprojectmyshop.product.models.dtos.exports.CategoryAndRandomProductsDTO;
import org.example.finalprojectmyshop.product.models.entities.Category;

import java.util.Set;

public interface CategoryService {

    Set<Category> getAllCategories();

    Set<CategoryAndRandomProductsDTO> getCategoriesWithRandomProducts();
}
