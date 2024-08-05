package org.example.finalprojectmyshop.product.service.impl;

import org.example.finalprojectmyshop.mediaFile.service.MediaFileService;
import org.example.finalprojectmyshop.product.models.dtos.exports.ProductDetailsSecondaryCategoryDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.RandomProductsDTO;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.product.models.entities.Rating;
import org.example.finalprojectmyshop.product.models.entities.Review;
import org.example.finalprojectmyshop.product.models.entities.SecondaryCategory;
import org.example.finalprojectmyshop.product.models.enums.SecondaryCategoryName;
import org.example.finalprojectmyshop.product.repository.SecondaryCategoryRepository;
import org.example.finalprojectmyshop.product.service.ProductService;
import org.example.finalprojectmyshop.product.service.SecondaryCategoryService;
import org.example.finalprojectmyshop.user.service.impl.UserHelperService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SecondaryCategoryServiceImpl implements SecondaryCategoryService {

    private final SecondaryCategoryRepository secondaryCategoryRepository;
    private final ProductService productService;
    private final UserHelperService userHelperService;

    public SecondaryCategoryServiceImpl(SecondaryCategoryRepository secondaryCategoryRepository, ProductService productService, UserHelperService userHelperService) {
        this.secondaryCategoryRepository = secondaryCategoryRepository;
        this.productService = productService;
        this.userHelperService = userHelperService;
    }

    @Override
    public void save(SecondaryCategory secondaryCategory) {
        this.secondaryCategoryRepository.save(secondaryCategory);
    }

    @Override
    public ProductDetailsSecondaryCategoryDTO findCategoryByProductId(long id) {
        Product product = this.productService.findProductEntityById(id);
        SecondaryCategory secondaryCategory = this.secondaryCategoryRepository.findByName(product.getSecondaryCategory().getName());

        ProductDetailsSecondaryCategoryDTO productDetailsSecondaryCategoryDTO = this.toProductDetailsSecondaryCategoryDTO(secondaryCategory);

        return productDetailsSecondaryCategoryDTO;
    }

    @Override
    public SecondaryCategory findSecondaryCategoryEntityByName(SecondaryCategoryName name) {
        return this.secondaryCategoryRepository.findByName(name);
    }

    private ProductDetailsSecondaryCategoryDTO toProductDetailsSecondaryCategoryDTO(SecondaryCategory category) {
        ProductDetailsSecondaryCategoryDTO productDetailsSecondaryCategoryDTO = new ProductDetailsSecondaryCategoryDTO();

        category.getProducts()
                .forEach(product -> {
                    RandomProductsDTO randomProductsDTO = this.toRandomProductsDTO(product);
                    productDetailsSecondaryCategoryDTO.getProducts().add(randomProductsDTO);
                });

        return productDetailsSecondaryCategoryDTO;
    }

    private RandomProductsDTO toRandomProductsDTO(Product product) {
        RandomProductsDTO randomProductsDTO = new RandomProductsDTO();
        randomProductsDTO.setId(product.getId());
        randomProductsDTO.setName(product.getName());

        randomProductsDTO.setImageUrl(product.getMainImage().getImageUrl());

        randomProductsDTO.setPrice(product.getPrice());

        if (!product.getReviews().isEmpty()) {
            double rating = product.getReviews()
                    .stream()
                    .map(Review::getRating)
                    .collect(Collectors.toSet())
                    .stream()
                    .map(Rating::getRating)
                    .reduce(0.0, Double::sum) / product.getReviews().size();

            randomProductsDTO.setRating(rating);
        }

        randomProductsDTO.setReviewsCount(product.getReviews().size());

        try {
            boolean isFavorite = this.userHelperService.getUser().getFavorites()
                    .stream()
                    .map(Product::getId)
                    .anyMatch(productId -> productId == product.getId());

            randomProductsDTO.setFavorite(isFavorite);
        } catch (Exception ex) {
            randomProductsDTO.setFavorite(false);
        }

        return randomProductsDTO;
    }
}
