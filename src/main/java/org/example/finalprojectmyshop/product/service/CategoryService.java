package org.example.finalprojectmyshop.product.service;

import org.example.finalprojectmyshop.product.models.entities.Category;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CategoryService {

    Set<Category> getAllCategories();

}
