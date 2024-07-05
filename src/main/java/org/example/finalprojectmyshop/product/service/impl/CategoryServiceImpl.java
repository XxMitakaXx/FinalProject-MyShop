package org.example.finalprojectmyshop.product.service.impl;

import org.example.finalprojectmyshop.product.models.entities.Category;
import org.example.finalprojectmyshop.product.models.entities.SecondaryCategory;
import org.example.finalprojectmyshop.product.models.enums.CategoryName;
import org.example.finalprojectmyshop.product.models.enums.SecondaryCategoryName;
import org.example.finalprojectmyshop.product.repository.CategoryRepository;
import org.example.finalprojectmyshop.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getAllCategories() {
        return this.categoryRepository.getAll();
    }
}
