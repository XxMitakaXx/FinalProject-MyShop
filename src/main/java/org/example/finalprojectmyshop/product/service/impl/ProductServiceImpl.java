package org.example.finalprojectmyshop.product.service.impl;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFile;
import org.example.finalprojectmyshop.mediaFile.models.enums.ImageType;
import org.example.finalprojectmyshop.mediaFile.repository.MediaFileRepository;
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
import org.example.finalprojectmyshop.user.service.UserService;
import org.example.finalprojectmyshop.user.service.impl.CurrentUser;
import org.example.finalprojectmyshop.user.service.impl.UserServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SecondaryCategoryRepository secondaryCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final ProductPropertyRepository productPropertyRepository;
    private final UserService userService;
    private final MediaFileService mediaFileService;
    private final CurrentUser currentUser;

    public ProductServiceImpl(ProductRepository productRepository, SecondaryCategoryRepository secondaryCategoryRepository, CategoryRepository categoryRepository, ProductPropertyRepository productPropertyRepository, UserService userService, MediaFileService mediaFileService, CurrentUser currentUser) {
        this.productRepository = productRepository;
        this.secondaryCategoryRepository = secondaryCategoryRepository;
        this.categoryRepository = categoryRepository;
        this.productPropertyRepository = productPropertyRepository;
        this.userService = userService;
        this.mediaFileService = mediaFileService;
        this.currentUser = currentUser;
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
                                .setName(property.getName().trim())
                                .setValue(property.getValue().trim());

                        product.getProperties().add(productProperty);

                        this.productPropertyRepository.save(productProperty);
                    }
                });

        String mainImageUrl = this.mediaFileService.upload(addProductDTO.getFirstImage(), ImageType.PRODUCT, product.getName());
        MediaFile mainImage = new MediaFile();
        mainImage.setUrl(mainImageUrl);
        this.mediaFileService.save(mainImage);

        product.setMainImage(mainImage);


        List<MultipartFile> images = List.of(
                addProductDTO.getFirstImage(),
                addProductDTO.getSecondImage(),
                addProductDTO.getThirdImage(),
                addProductDTO.getFourthImage(),
                addProductDTO.getFifthImage()
        );

        Set<String> urls = new HashSet<>();

        images.forEach(image -> {

            String url = this.mediaFileService.upload(image, ImageType.PRODUCT, product.getName());
            urls.add(url);
        });

        urls.forEach(url -> {
            MediaFile mediaFile = new MediaFile();
            mediaFile.setUrl(url);

            this.mediaFileService.save(mediaFile);
            product.getImages().add(mediaFile);
        });

        category.getProducts().add(product);

        this.productRepository.save(product);
        this.secondaryCategoryRepository.save(category);
    }

    @Override
    public void addProductToFavorites(long id) {
        Optional<Product> optional = this.productRepository.findById(id);

        if (optional.isEmpty()) {
            return;
        }

        Product product = optional.get();
        boolean contains = this.currentUser.getUser().getFavorites()
                .stream()
                .map(Product::getId)
                .collect(Collectors.toSet())
                .contains(product.getId());

        if (!contains) {
            this.currentUser.getUser().getFavorites().add(product);
            this.userService.save(this.currentUser.getUser());
        }

    }
}
