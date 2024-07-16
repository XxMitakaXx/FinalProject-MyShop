package org.example.finalprojectmyshop.product.service.impl;

import org.example.finalprojectmyshop.mediaFile.models.enums.ImageType;
import org.example.finalprojectmyshop.mediaFile.service.MediaFileService;
import org.example.finalprojectmyshop.product.models.dtos.exports.ProductDetailsSecondaryCategoryDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.RandomProductsDTO;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.product.models.entities.Rating;
import org.example.finalprojectmyshop.product.models.entities.Review;
import org.example.finalprojectmyshop.product.models.entities.SecondaryCategory;
import org.example.finalprojectmyshop.product.repository.SecondaryCategoryRepository;
import org.example.finalprojectmyshop.product.service.ProductService;
import org.example.finalprojectmyshop.product.service.SecondaryCategoryService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SecondaryCategoryServiceImpl implements SecondaryCategoryService {

    private final SecondaryCategoryRepository secondaryCategoryRepository;
    private final ProductService productService;
    private final MediaFileService mediaFileService;

    public SecondaryCategoryServiceImpl(SecondaryCategoryRepository secondaryCategoryRepository, ProductService productService, MediaFileService mediaFileService) {
        this.secondaryCategoryRepository = secondaryCategoryRepository;
        this.productService = productService;
        this.mediaFileService = mediaFileService;
    }

    @Override
    public ProductDetailsSecondaryCategoryDTO findCategoryByProductId(long id) {
        Product product = this.productService.findProductEntityById(id);
        SecondaryCategory secondaryCategory = this.secondaryCategoryRepository.findByName(product.getSecondaryCategory().getName());

        ProductDetailsSecondaryCategoryDTO productDetailsSecondaryCategoryDTO = this.toProductDetailsSecondaryCategoryDTO(secondaryCategory);

        return productDetailsSecondaryCategoryDTO;
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

        String url = product.getMainImage().getUrl();

        randomProductsDTO.setImageUrl(url);
        this.mediaFileService.downloadFile(url, ImageType.PRODUCT, product.getName());

        randomProductsDTO.setPriceBeforePoint(Integer.parseInt(String.valueOf(product.getPrice()).split("\\.")[0]));
        randomProductsDTO.setPriceAfterPoint(Integer.parseInt(String.valueOf(product.getPrice()).split("\\.")[1]));

        if (!product.getReviews().isEmpty()) {
            double rating = product.getReviews()
                    .stream()
                    .map(Review::getRating)
                    .collect(Collectors.toSet())
                    .stream()
                    .map(Rating::getRating)
                    .reduce(0.0, Double::sum);

            randomProductsDTO.setRating(rating);
        }

        randomProductsDTO.setReviewsCount(product.getReviews().size());

        return randomProductsDTO;
    }
}
