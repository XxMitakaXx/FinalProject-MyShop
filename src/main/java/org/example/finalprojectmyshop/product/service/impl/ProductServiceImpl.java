package org.example.finalprojectmyshop.product.service.impl;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFile;
import org.example.finalprojectmyshop.mediaFile.models.enums.ImageType;
import org.example.finalprojectmyshop.mediaFile.service.MediaFileService;
import org.example.finalprojectmyshop.product.models.dtos.AddProductDTO;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.product.models.entities.ProductProperty;
import org.example.finalprojectmyshop.product.models.entities.SecondaryCategory;
import org.example.finalprojectmyshop.product.repository.CategoryRepository;
import org.example.finalprojectmyshop.product.repository.ProductPropertyRepository;
import org.example.finalprojectmyshop.product.repository.ProductRepository;
import org.example.finalprojectmyshop.product.repository.SecondaryCategoryRepository;
import org.example.finalprojectmyshop.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SecondaryCategoryRepository secondaryCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final ProductPropertyRepository productPropertyRepository;
    private final MediaFileService mediaFileService;

    public ProductServiceImpl(ProductRepository productRepository, SecondaryCategoryRepository secondaryCategoryRepository, CategoryRepository categoryRepository, ProductPropertyRepository productPropertyRepository, MediaFileService mediaFileService) {
        this.productRepository = productRepository;
        this.secondaryCategoryRepository = secondaryCategoryRepository;
        this.categoryRepository = categoryRepository;
        this.productPropertyRepository = productPropertyRepository;
        this.mediaFileService = mediaFileService;
    }

    @Override
    public void save(AddProductDTO addProductDTO) {
       SecondaryCategory category = this.secondaryCategoryRepository.findByName(addProductDTO.getSecondaryCategoryName());

        Product product = new Product();
        product
                .setName(addProductDTO.getName().trim())
                .setPrice(addProductDTO.getPrice())
                .setDescription(addProductDTO.getDescription().trim())
                .setSecondaryCategory(category)
                .setExpressShip(true)
                .setLeasing(true)
                .setQuantity(addProductDTO.getQuantity());

        addProductDTO.getProperties()
                .forEach(property -> {
                    if (!property.getName().isBlank() || !property.getValue().isBlank()) {
                        ProductProperty productProperty = new ProductProperty();
                        productProperty
                                .setName(property.getName())
                                .setValue(property.getValue());

                        product.getProperties().add(productProperty);

                        this.productPropertyRepository.save(productProperty);
                    }
                });

        List<File> images = List.of(
                addProductDTO.getFirstImage(),
                addProductDTO.getSecondImage(),
                addProductDTO.getThirdImage(),
                addProductDTO.getFourthImage(),
                addProductDTO.getFifthImage()
        );

        Set<String> urls = new HashSet<>();

        images.forEach(image -> {
            File file = new File(Path.of(image.getPath()).toUri());
            System.out.println();
            String url = this.mediaFileService.upload(file, ImageType.PRODUCT, product.getName());
            urls.add(url);
        });

        urls.forEach(url -> {
            MediaFile mediaFile = new MediaFile();
            mediaFile.setUrl(url);

            product.getImagesUrls().add(mediaFile);
        });

        this.productRepository.save(product);
    }
}
