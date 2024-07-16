package org.example.finalprojectmyshop.product.service.impl;

import org.example.finalprojectmyshop.mediaFile.models.enums.ImageType;
import org.example.finalprojectmyshop.mediaFile.service.MediaFileService;
import org.example.finalprojectmyshop.product.models.dtos.exports.CategoryAndRandomProductsDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.RandomProductsDTO;
import org.example.finalprojectmyshop.product.models.entities.Category;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.product.models.entities.Rating;
import org.example.finalprojectmyshop.product.models.entities.Review;
import org.example.finalprojectmyshop.product.repository.CategoryRepository;
import org.example.finalprojectmyshop.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MediaFileService mediaFileService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, MediaFileService mediaFileService) {
        this.categoryRepository = categoryRepository;
        this.mediaFileService = mediaFileService;
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
                                        boolean contains = categoryDto.getProducts()
                                                .stream()
                                                .map(RandomProductsDTO::getId)
                                                .collect(Collectors.toSet())
                                                .contains(product.getId());

                                        if (!contains) {
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

        String url = product.getMainImage().getUrl();

        productDTO.setImageUrl(url);
        this.mediaFileService.downloadFile(url, ImageType.PRODUCT, product.getName());

        productDTO.setPriceBeforePoint(Integer.parseInt(String.valueOf(product.getPrice()).split("\\.")[0]));
        productDTO.setPriceAfterPoint(Integer.parseInt(String.valueOf(product.getPrice()).split("\\.")[1]));

        if (!product.getReviews().isEmpty()) {
            double rating = product.getReviews()
                    .stream()
                    .map(Review::getRating)
                    .collect(Collectors.toSet())
                    .stream()
                    .map(Rating::getRating)
                    .reduce(0.0, Double::sum);

            productDTO.setRating(rating);
        }

        productDTO.setReviewsCount(product.getReviews().size());

        return productDTO;
    }
}
