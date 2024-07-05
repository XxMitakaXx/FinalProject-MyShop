package org.example.finalprojectmyshop.product.service;

import org.example.finalprojectmyshop.product.models.entities.Category;
import org.example.finalprojectmyshop.product.models.entities.SecondaryCategory;
import org.example.finalprojectmyshop.product.models.enums.CategoryName;
import org.example.finalprojectmyshop.product.models.enums.SecondaryCategoryName;
import org.example.finalprojectmyshop.product.repository.CategoryRepository;
import org.example.finalprojectmyshop.product.repository.SecondaryCategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InitCategories implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final SecondaryCategoryRepository secondaryCategoryRepository;

    private final CategoryName[] categories = CategoryName.values();
    private final SecondaryCategoryName[] secondaryCategoryNames = SecondaryCategoryName.values();

    public InitCategories(CategoryRepository categoryRepository, SecondaryCategoryRepository secondaryCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.secondaryCategoryRepository = secondaryCategoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.categoryRepository.count() == 0) {
            Arrays.stream(categories)
                    .forEach(categoryName -> {
                        Category category = new Category();
                        category.setName(categoryName.getName());

                        Arrays.stream(secondaryCategoryNames)
                                .forEach(secondaryCategoryName -> {
                                    if (secondaryCategoryName.getCategoryName().equals(categoryName)) {
                                        SecondaryCategory secondaryCategory = new SecondaryCategory();
                                        secondaryCategory.setName(secondaryCategoryName.getName());
                                        secondaryCategory.setCategory(category);

//                                        this.secondaryCategoryRepository.save(secondaryCategory);

                                        category.getSecondaryCategories().add(secondaryCategory);


                                        this.categoryRepository.save(category);
                                        this.secondaryCategoryRepository.save(secondaryCategory);
                                    }
                                });
                    });
        }
    }
}
