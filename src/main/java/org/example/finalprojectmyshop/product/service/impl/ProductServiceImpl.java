package org.example.finalprojectmyshop.product.service.impl;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFileEntity;
import org.example.finalprojectmyshop.mediaFile.service.ImagesHelperService;
import org.example.finalprojectmyshop.order.models.entities.CartEntity;
import org.example.finalprojectmyshop.order.models.entities.ProductInCartEntity;
import org.example.finalprojectmyshop.order.service.CartService;
import org.example.finalprojectmyshop.order.service.ProductInCartService;
import org.example.finalprojectmyshop.product.models.dtos.exports.*;
import org.example.finalprojectmyshop.product.models.dtos.imports.AddProductDTO;
import org.example.finalprojectmyshop.product.models.entities.*;
import org.example.finalprojectmyshop.product.repository.ProductPropertyRepository;
import org.example.finalprojectmyshop.product.repository.ProductRepository;
import org.example.finalprojectmyshop.product.repository.SecondaryCategoryRepository;
import org.example.finalprojectmyshop.product.service.ProductService;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.service.UserService;
import org.example.finalprojectmyshop.user.service.impl.UserHelperService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SecondaryCategoryRepository secondaryCategoryRepository;
    private final ProductPropertyRepository productPropertyRepository;
    private final ImagesHelperService imagesHelperService;
    private final UserService userService;
    private final UserHelperService userHelperService;
    private final CartService cartService;
    private final ProductInCartService productInCartService;

    public ProductServiceImpl(ProductRepository productRepository, SecondaryCategoryRepository secondaryCategoryRepository, ProductPropertyRepository productPropertyRepository, ImagesHelperService imagesHelperService, UserService userService, UserHelperService userHelperService, CartService cartService, ProductInCartService productInCartService) {
        this.productRepository = productRepository;
        this.secondaryCategoryRepository = secondaryCategoryRepository;
        this.productPropertyRepository = productPropertyRepository;
        this.imagesHelperService = imagesHelperService;
        this.userService = userService;
        this.userHelperService = userHelperService;
        this.cartService = cartService;
        this.productInCartService = productInCartService;
    }

    @Override
    public void save(AddProductDTO addProductDTO) throws IOException {
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

        MediaFileEntity mediaFile = this.imagesHelperService.saveImage(addProductDTO.getFirstImage());
        product.setMainImage(mediaFile);


        List<MultipartFile> images = List.of(
                addProductDTO.getFirstImage(),
                addProductDTO.getSecondImage(),
                addProductDTO.getThirdImage(),
                addProductDTO.getFourthImage(),
                addProductDTO.getFifthImage()
        );

        images.forEach(image -> {
            try {
                MediaFileEntity mediaFile1 = this.imagesHelperService.saveImage(image);
                product.getImages().add(mediaFile1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        category.getProducts().add(product);

        this.productRepository.save(product);
        this.secondaryCategoryRepository.save(category);
    }

    @Override
    public void save(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void addProductToFavorites(long id) {
        UserEntity user = this.userHelperService.getUser();
        Optional<Product> optional = this.productRepository.findById(id);

        if (optional.isPresent()) {
            Product productEntity = optional.get();

            boolean contain = user
                    .getFavorites()
                    .stream()
                    .map(Product::getId)
                    .anyMatch(productInCartEntityId -> productInCartEntityId.equals(productEntity.getId()));

            if (!contain) {
                user.getFavorites().add(productEntity);

                this.userService.save(user);
            }
        }
    }

    @Override
    public void deleteProductFromFavorites(long id) {
        UserEntity user = this.userHelperService.getUser();
        Optional<Product> optional = this.productRepository.findById(id);

        if (optional.isPresent()) {
            Product productEntity = optional.get();

            boolean contain = user
                    .getFavorites()
                    .stream()
                    .map(Product::getId)
                    .anyMatch(productInCartEntityId -> productInCartEntityId.equals(productEntity.getId()));

            if (contain) {
                user.getFavorites().remove(productEntity);

                this.userService.save(user);
            }
        }
    }

    @Override
    public void addProductToCart(long id) {
        Product product = this.productRepository.findById(id).get();

        UserEntity user = this.userHelperService.getUser();

        boolean containInCart = user
                .getCart()
                .getProductsInCart()
                .stream()
                .map(productInCartEntity -> productInCartEntity.getProduct().getId())
                .anyMatch(productInCartEntityId -> productInCartEntityId.equals(product.getId()));

        if (!containInCart) {
            ProductInCartEntity productInCartEntity = new ProductInCartEntity();
            productInCartEntity.setProduct(product);
            productInCartEntity.setCount(1);
            this.productInCartService.save(productInCartEntity);

            CartEntity cart = user.getCart();
            cart.getProductsInCart().add(productInCartEntity);
            this.cartService.save(cart);

            user.setCart(cart);


            boolean containInFavorites = user
                    .getFavorites()
                    .stream()
                    .map(Product::getId)
                    .anyMatch(productInFavorites -> productInFavorites.equals(product.getId()));

            if (containInFavorites) {
                user.getFavorites().remove(product);
            }

            this.userService.save(user);
        }
    }

    @Override
    public void deleteProductFromCartByProduct(long id) {
        ProductInCartEntity product = this.productInCartService.findById(id);

        UserEntity user = this.userHelperService.getUser();

        boolean contain = false;

        Set<ProductInCartEntity> productsInCart = user.getCart().getProductsInCart();
        for (ProductInCartEntity productInCartEntity : productsInCart) {
            if (Integer.parseInt(String.valueOf(productInCartEntity.getId())) == Integer.parseInt(String.valueOf(product.getId()))) {
                contain = true;
                break;
            }
        }

        if (contain) {
            CartEntity cart = user.getCart();

            Set<ProductInCartEntity> productInCartEntities = cart.getProductsInCart()
                    .stream()
                    .filter(productInCartEntity -> Integer.parseInt(String.valueOf(productInCartEntity.getId())) != Integer.parseInt(String.valueOf(product.getId())))
                    .collect(Collectors.toSet());

            cart.setProductsInCart(productInCartEntities);
            this.cartService.save(cart);

            user.setCart(cart);

            this.userService.save(user);

            this.productInCartService.deleteById(id);
        }
    }

    @Override
    public ProductDetailsDTO findById(long id) {
        Optional<Product> byId = this.productRepository.findById(id);

        if (byId.isEmpty()) {
            return null;
        }

        Product product = byId.get();
        ProductDetailsDTO productDetailsDTO = this.toProductDetailsDTO(product);

        return productDetailsDTO;
    }

    @Override
    public Product findProductEntityById(long id) {
        Optional<Product> optional = this.productRepository.findById(id);
        return optional.orElseGet(Product::new);
    }

    @Override
    public Set<FavoriteProductDTO> findFavoriteProducts() {
        UserEntity user = this.userHelperService.getUser();

        Set<FavoriteProductDTO> favoritesProducts = user.getFavorites()
                .stream()
                .map(this::toFavoriteProductDTO)
                .collect(Collectors.toSet());

        return favoritesProducts;
    }

    @Override
    public void decreaseProductQuantity(long productId, int quantity) {
        Optional<Product> optional = this.productRepository.findById(productId);

        if (optional.isPresent()) {
            Product product = optional.get();

            if (product.getQuantity() <= quantity) {
                product.setQuantity(0);
            } else {
                product.setQuantity(product.getQuantity() - quantity);
            }

            this.productRepository.save(product);
        }
    }

    @Override
    public FoundedProductsForDeleteDTO searchProductsForDelete(String name) {
        Set<Product> foundedProductEntities = this.productRepository.findProductsByName(name);

        FoundedProductsForDeleteDTO foundedProductsForDeleteDTO = new FoundedProductsForDeleteDTO();

        foundedProductEntities.forEach(product -> {
            FoundedProductForDeleteDTO foundedProductForDeleteDTO = this.toFoundedProductFroDeleteDTO(product);
            foundedProductsForDeleteDTO.getFoundedProducts().add(foundedProductForDeleteDTO);
        });

        return foundedProductsForDeleteDTO;
    }

    @Override
    public FoundedProductsByNameDTO findProductsByName(String name) {
        Set<Product> products = this.productRepository.findProductsByName(name);

        FoundedProductsByNameDTO foundedProductsByNameDTO = new FoundedProductsByNameDTO();

        products.forEach(product -> {
            FoundedProductByNameDTO foundedProductByNameDTO = this.toFoundedProductForDeleteDTO(product);
            foundedProductsByNameDTO.getFoundedProductByNameDTO().add(foundedProductByNameDTO);
        });

        return foundedProductsByNameDTO;
    }

    private FoundedProductByNameDTO toFoundedProductForDeleteDTO(Product product) {
        FoundedProductByNameDTO foundedProductByNameDTO = new FoundedProductByNameDTO();

        foundedProductByNameDTO.setId(product.getId());
        foundedProductByNameDTO.setName(product.getName());
        foundedProductByNameDTO.setImageUrl(product.getMainImage().getImageUrl());

        return foundedProductByNameDTO;
    }

    private FoundedProductForDeleteDTO toFoundedProductFroDeleteDTO(Product product) {
        FoundedProductForDeleteDTO foundedProductForDeleteDTO = new FoundedProductForDeleteDTO();

        foundedProductForDeleteDTO.setId(product.getId());
        foundedProductForDeleteDTO.setName(product.getName());
        foundedProductForDeleteDTO.setImageUrl(product.getMainImage().getImageUrl());

        return foundedProductForDeleteDTO;
    }

    private FavoriteProductDTO toFavoriteProductDTO(Product product) {
        FavoriteProductDTO favoriteProductDTO = new FavoriteProductDTO();

        favoriteProductDTO.setId(product.getId());
        favoriteProductDTO.setImageUrl(product.getMainImage().getImageUrl());
        favoriteProductDTO.setName(product.getName());
        favoriteProductDTO.setPrice(product.getPrice());

        return favoriteProductDTO;
    }

    private ProductDetailsDTO toProductDetailsDTO(Product product) {
        ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO();

        productDetailsDTO.setId(product.getId());
        productDetailsDTO.setName(product.getName());
        productDetailsDTO.setMainImageUrl(product.getMainImage().getImageUrl());
        productDetailsDTO.setOriginalPrice(product.getPrice());
        productDetailsDTO.setDiscountPrice(product.getDiscountPrice());

        product.getImages()
                .forEach(image -> {
                    productDetailsDTO.getImagesUrls().add(image.getImageUrl());
                });

        productDetailsDTO.setDiscountPrice(product.getDiscountPrice());

        product.getReviews()
                .forEach(review -> {
                    ReviewDataDTO reviewDataDTO = toReviewDataDTO(review);
                    productDetailsDTO.getReviews().add(reviewDataDTO);
                });

        int reviewCount = product.getReviews().size();
        double productRating = product.getReviews()
                .stream()
                .map(Review::getRating)
                .collect(Collectors.toSet())
                .stream()
                .map(Rating::getRating)
                .reduce(0.0, Double::sum) / reviewCount;

        if (productRating > 0) {
            productDetailsDTO.setRating(productRating);
        } else {
            productDetailsDTO.setRating(0.0);
        }

        productDetailsDTO.setReviewsCount(reviewCount);

        int fiveStarsReviewCount = product.getReviews()
                .stream()
                .filter(review -> review.getRating().getRating() > 4.5)
                .collect(Collectors.toSet())
                .size();

        productDetailsDTO.setFiveStarsReviewsCount(fiveStarsReviewCount);

        double fiveStarPercent = ((double) fiveStarsReviewCount / reviewCount) * 100;
        productDetailsDTO.setFiveStarPercent(fiveStarPercent);

        int fourStarsReviewCount = product.getReviews()
                .stream()
                .filter(review -> review.getRating().getRating() >= 4.0 && review.getRating().getRating() < 4.5)
                .collect(Collectors.toSet())
                .size();

        productDetailsDTO.setFourStarsReviewsCount(fourStarsReviewCount);

        double fourStarPercent = ((double) fourStarsReviewCount / reviewCount) * 100;
        productDetailsDTO.setFourStarPercent(fourStarPercent);

        int threeStarsReviewCount = product.getReviews()
                .stream()
                .filter(review -> review.getRating().getRating() >= 3.0 && review.getRating().getRating() < 4.0)
                .collect(Collectors.toSet())
                .size();

        productDetailsDTO.setThreeStarsReviewsCount(threeStarsReviewCount);

        double threeStarPercent = ((double) threeStarsReviewCount / reviewCount) * 100;
        productDetailsDTO.setThreeStarPercent(threeStarPercent);

        int twoStarsReviewCount = product.getReviews()
                .stream()
                .filter(review -> review.getRating().getRating() >= 2.0 && review.getRating().getRating() < 3.0)
                .collect(Collectors.toSet())
                .size();

        productDetailsDTO.setTwoStarsReviewsCount(twoStarsReviewCount);

        double twoStarPercent = ((double) twoStarsReviewCount / reviewCount) * 100;
        productDetailsDTO.setTwoStarPercent(twoStarPercent);

        int oneStarsReviewCount = product.getReviews()
                .stream()
                .filter(review -> review.getRating().getRating() >= 1 && review.getRating().getRating() < 2.0)
                .collect(Collectors.toSet())
                .size();

        productDetailsDTO.setOneStarsReviewsCount(oneStarsReviewCount);

        double oneStarPercent = ((double) oneStarsReviewCount / reviewCount) * 100;
        productDetailsDTO.setOneStarPercent(oneStarPercent);

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
        reviewUserDataDTO.setProfilePictureUrl(userEntity.getProfilePicture().getImageUrl());

        return reviewUserDataDTO;
    }

    private ProductDetailsPropertyDTO toProductDetailsPropertyDTO(ProductProperty productProperty) {
        ProductDetailsPropertyDTO productDetailsPropertyDTO = new ProductDetailsPropertyDTO();

        productDetailsPropertyDTO.setName(productProperty.getName());
        productDetailsPropertyDTO.setValue(productProperty.getValue());

        return productDetailsPropertyDTO;
    }
}
