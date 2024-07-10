package org.example.finalprojectmyshop.product.service.impl;

import org.example.finalprojectmyshop.product.models.dtos.CategoryAndRandomProductsDTO;
import org.example.finalprojectmyshop.product.models.dtos.RandomProductsDTO;
import org.example.finalprojectmyshop.product.models.entities.Category;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.product.models.entities.Rating;
import org.example.finalprojectmyshop.product.repository.CategoryRepository;
import org.example.finalprojectmyshop.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

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

    @Override
    public Set<CategoryAndRandomProductsDTO> getCategoriesWithRandomProducts() {
        Set<CategoryAndRandomProductsDTO> categoriesAndProducts = new HashSet<>();

        this.categoryRepository
                .findAll()
                .forEach(category -> {
                    CategoryAndRandomProductsDTO categoryDTO = this.toCategoryAndRandomProductsDTO(category);

                    categoriesAndProducts.add(categoryDTO);
                });

        return categoriesAndProducts;
    }

    private CategoryAndRandomProductsDTO toCategoryAndRandomProductsDTO(Category category) {
        CategoryAndRandomProductsDTO categoryDto = new CategoryAndRandomProductsDTO();
        categoryDto.setName(category.getName().getName());
        categoryDto.setId(category.getId());

        category.getSecondaryCategories()
                .forEach(secondaryCategory -> {
                    if (!secondaryCategory.getProducts().isEmpty()) {
                        for (int i = 1; i <= 5; i++) {
                            ThreadLocalRandom random = ThreadLocalRandom.current();
                            long productId = random.nextLong(secondaryCategory.getProducts().size() + 1);

                            secondaryCategory.getProducts()
                                    .forEach(product -> {
                                        if (product.getId() == productId) {
                                            RandomProductsDTO productDTO = this.mapToRandomProductDTO(product);

                                            categoryDto.getProducts().add(productDTO);
                                        }
                                    });
                        }
                    }
                });

        return categoryDto;
    }

    private RandomProductsDTO mapToRandomProductDTO(Product product) {
        RandomProductsDTO productDTO = new RandomProductsDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());

        String url = product.getImagesUrls()
                .stream()
                .findFirst()
                .orElse(null).getUrl();

        productDTO.setImageUrl(url);
        productDTO.setPrice(product.getPrice());

        if (!product.getRatings().isEmpty()) {
            double rating = product.getRatings()
                    .stream()
                    .map(Rating::getRating)
                    .reduce(0.0, Double::sum);

            productDTO.setRating(rating);
        }

        productDTO.setReviewsCount(product.getRatings().size());

        return productDTO;
    }
}
