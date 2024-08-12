package org.example.finalprojectmyshop.product.service.impl;

import org.example.finalprojectmyshop.product.models.dtos.exports.CategoryAndRandomProductsDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.RandomProductsDTO;
import org.example.finalprojectmyshop.product.models.entities.Category;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.product.models.entities.Rating;
import org.example.finalprojectmyshop.product.models.entities.Review;
import org.example.finalprojectmyshop.product.repository.CategoryRepository;
import org.example.finalprojectmyshop.product.service.CategoryService;
import org.example.finalprojectmyshop.user.service.impl.UserHelperService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserHelperService userHelperService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, UserHelperService userHelperService) {
        this.categoryRepository = categoryRepository;
        this.userHelperService = userHelperService;
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
                        if (secondaryCategory.getProducts().size() > 7) {
                            int addedProductCount = 0;
                            for (int i = 1; addedProductCount <= 7; i++) {
                                Set<Product> secondaryCategoryProducts = secondaryCategory.getProducts();

                                ThreadLocalRandom random = ThreadLocalRandom.current();
                                int randomProductNumber = random.nextInt(secondaryCategory.getProducts().size() + 1);
                                int currentProductNumber = 0;

                                for (Product product : secondaryCategoryProducts) {
                                    currentProductNumber++;

                                    if (currentProductNumber == randomProductNumber) {

                                        boolean contains = secondaryCategory.getProducts()
                                                .stream()
                                                .map(Product::getId)
                                                .collect(Collectors.toSet())
                                                .stream()
                                                .anyMatch(id -> id == randomProductNumber);

                                        if (!contains) {
                                            secondaryCategory.getProducts().add(product);
                                            addedProductCount++;
                                        }
                                    }
                                }
                            }
                        } else {
                            Set<RandomProductsDTO> randomProductsDTOS = secondaryCategory.getProducts()
                                    .stream()
                                    .map(this::mapToRandomProductDTO)
                                    .collect(Collectors.toSet());

                            categoryDto.getProducts().addAll(randomProductsDTOS);
                        }
                    }
                });

        return categoryDto;
    }

    private RandomProductsDTO mapToRandomProductDTO(Product product) {
        RandomProductsDTO productDTO = new RandomProductsDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setImageUrl(product.getMainImage().getImageUrl());


        productDTO.setPrice(product.getPrice());

        if (!product.getReviews().isEmpty()) {
            double rating = product.getReviews()
                    .stream()
                    .map(Review::getRating)
                    .collect(Collectors.toSet())
                    .stream()
                    .map(Rating::getRating)
                    .reduce(0.0, Double::sum) / product.getReviews().size();

            productDTO.setRating(rating);
        } else {
            productDTO.setRating(0.0);
        }

        productDTO.setReviewsCount(product.getReviews().size());

        try {
            boolean isFavorite = this.userHelperService.getUser().getFavorites()
                    .stream()
                    .map(Product::getId)
                    .anyMatch(productId -> productId == product.getId());

            productDTO.setFavorite(isFavorite);
        } catch (Exception ex) {
            productDTO.setFavorite(false);
        }

        return productDTO;
    }
}
