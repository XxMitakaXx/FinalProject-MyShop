package org.example.finalprojectmyshop.product.service.impl;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFile;
import org.example.finalprojectmyshop.mediaFile.models.enums.ImageType;
import org.example.finalprojectmyshop.mediaFile.service.MediaFileService;
import org.example.finalprojectmyshop.product.models.dtos.exports.ProductDetailsDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.ProductDetailsPropertyDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.ReviewDataDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.ReviewUserDataDTO;
import org.example.finalprojectmyshop.product.models.dtos.imports.AddProductDTO;
import org.example.finalprojectmyshop.product.models.entities.*;
import org.example.finalprojectmyshop.product.repository.CategoryRepository;
import org.example.finalprojectmyshop.product.repository.ProductPropertyRepository;
import org.example.finalprojectmyshop.product.repository.ProductRepository;
import org.example.finalprojectmyshop.product.repository.SecondaryCategoryRepository;
import org.example.finalprojectmyshop.product.service.ProductService;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.service.UserService;
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

    public ProductServiceImpl(ProductRepository productRepository, SecondaryCategoryRepository secondaryCategoryRepository, CategoryRepository categoryRepository, ProductPropertyRepository productPropertyRepository, UserService userService, MediaFileService mediaFileService) {
        this.productRepository = productRepository;
        this.secondaryCategoryRepository = secondaryCategoryRepository;
        this.categoryRepository = categoryRepository;
        this.productPropertyRepository = productPropertyRepository;
        this.userService = userService;
        this.mediaFileService = mediaFileService;
    }

    @Override
    public void save(AddProductDTO addProductDTO) {
       SecondaryCategory category = this.secondaryCategoryRepository.findByName(addProductDTO.getSecondaryCategoryName());

        Product product = new Product();
        product
                .setName(addProductDTO.getName().trim())
                .setPrice(addProductDTO.getPrice())
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
//        Optional<Product> optional = this.productRepository.findById(id);
//
//        if (optional.isEmpty()) {
//            return;
//        }
//
//        Product product = optional.get();
//        boolean contains = this.currentUser.getUser().getFavorites()
//                .stream()
//                .map(Product::getId)
//                .collect(Collectors.toSet())
//                .contains(product.getId());
//
//        if (!contains) {
//            this.currentUser.getUser().getFavorites().add(product);
//            this.userService.save(this.currentUser.getUser());
//        }

    }

    @Override
    public ProductDetailsDTO findById(long id) {
        Optional<Product> byId = this.productRepository.findById(id);

        if (byId.isEmpty()) {
            return null;
        }

        Product product = byId.get();
        ProductDetailsDTO productDetailsDTO = toProductDetailsDTO(product);

        return productDetailsDTO;
    }

    @Override
    public Product findProductEntityById(long id) {
        Optional<Product> optional = this.productRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return optional.get();
    }

    private ProductDetailsDTO toProductDetailsDTO(Product product) {
        ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO();

        productDetailsDTO.setId(product.getId());
        productDetailsDTO.setName(product.getName());
        productDetailsDTO.setMainImageUrl(product.getMainImage().getUrl());
        productDetailsDTO.setOriginalPrice(product.getPrice());
        productDetailsDTO.setDiscountPrice(product.getDiscountPrice());
        this.mediaFileService.downloadFile(product.getMainImage().getUrl(), ImageType.PRODUCT, product.getName());

        product.getImages()
                .forEach(image -> {
                    productDetailsDTO.getImagesUrls().add(image.getUrl());
                    this.mediaFileService.downloadFile(image.getUrl(), ImageType.PRODUCT, product.getName());
                });

        productDetailsDTO.setDiscountPrice(product.getDiscountPrice());

        product.getReviews()
                .forEach(review -> {
                    ReviewDataDTO reviewDataDTO = toReviewDataDTO(review);
                    productDetailsDTO.getReviews().add(reviewDataDTO);
                });

        double productRating = product.getReviews()
                .stream()
                .map(Review::getRating)
                .collect(Collectors.toSet())
                .stream()
                .map(Rating::getRating)
                .reduce(0.0, Double::sum) / product.getReviews().size();

        if (productRating > 0) {
            productDetailsDTO.setRating(productRating);
        } else {
            productDetailsDTO.setRating(0.0);
        }

        productDetailsDTO.setReviewsCount(product.getReviews().size());

        int fiveStarsReviewCount = product.getReviews()
                .stream()
                .filter(review -> review.getRating().getRating() > 4.5)
                .collect(Collectors.toSet())
                .size();

        productDetailsDTO.setFiveStarsReviewsCount(fiveStarsReviewCount);

        int fourStarsReviewCount = product.getReviews()
                .stream()
                .filter(review -> review.getRating().getRating() >= 4.0 && review.getRating().getRating() < 4.5)
                .collect(Collectors.toSet())
                .size();

        productDetailsDTO.setFourStarsReviewsCount(fourStarsReviewCount);

        int threeStarsReviewCount = product.getReviews()
                .stream()
                .filter(review -> review.getRating().getRating() >= 3.0 && review.getRating().getRating() < 4.0)
                .collect(Collectors.toSet())
                .size();

        productDetailsDTO.setThreeStarsReviewsCount(threeStarsReviewCount);

        int twoStarsReviewCount = product.getReviews()
                .stream()
                .filter(review -> review.getRating().getRating() >= 2.0 && review.getRating().getRating() < 3.0)
                .collect(Collectors.toSet())
                .size();

        productDetailsDTO.setTwoStarsReviewsCount(twoStarsReviewCount);

        int oneStarsReviewCount = product.getReviews()
                .stream()
                .filter(review -> review.getRating().getRating() >= 1 && review.getRating().getRating() < 2.0)
                .collect(Collectors.toSet())
                .size();

        productDetailsDTO.setOneStarsReviewsCount(oneStarsReviewCount);

        product.getProperties()
                .forEach(productProperty -> {
                    ProductDetailsPropertyDTO productDetailsPropertyDTO = this.toProductDetailsPropertyDTO(productProperty);
                    productDetailsDTO.getProperties().add(productDetailsPropertyDTO);
                });

        productDetailsDTO.setAvailable(product.isAvailable());

        return productDetailsDTO;
    }

    private ReviewDataDTO toReviewDataDTO(Review review) {
        ReviewDataDTO reviewDataDTO = new ReviewDataDTO();

        reviewDataDTO.setTitle(review.getTitle());
        reviewDataDTO.setText(review.getDescription());
        reviewDataDTO.setPostDate(review.getDate().toString());

        ReviewUserDataDTO reviewUserDataDTO = toReviewUserDataDTO(review.getUser());
        reviewDataDTO.setUserData(reviewUserDataDTO);

        reviewDataDTO.setRating(review.getRating().getRating());

        return reviewDataDTO;
    }

    private ReviewUserDataDTO toReviewUserDataDTO(UserEntity userEntity) {
        ReviewUserDataDTO reviewUserDataDTO = new ReviewUserDataDTO();

        reviewUserDataDTO.setFullName(userEntity.getFirstName() + " " + userEntity.getLastName());
        reviewUserDataDTO.setProfilePictureUrl(userEntity.getProfilePicture().getUrl());
        this.mediaFileService.downloadFile(userEntity.getProfilePicture().getUrl(), ImageType.USER);

        return reviewUserDataDTO;
    }

    private ProductDetailsPropertyDTO toProductDetailsPropertyDTO(ProductProperty productProperty) {
        ProductDetailsPropertyDTO productDetailsPropertyDTO = new ProductDetailsPropertyDTO();

        productDetailsPropertyDTO.setName(productProperty.getName());
        productDetailsPropertyDTO.setValue(productProperty.getValue());

        return productDetailsPropertyDTO;
    }
}
