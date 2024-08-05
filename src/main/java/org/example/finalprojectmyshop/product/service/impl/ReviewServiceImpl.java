package org.example.finalprojectmyshop.product.service.impl;

import org.example.finalprojectmyshop.product.models.dtos.exports.ReviewDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.ReviewProductDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.ReviewUserDTO;
import org.example.finalprojectmyshop.product.models.dtos.imports.AddReviewDTO;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.product.models.entities.Rating;
import org.example.finalprojectmyshop.product.models.entities.Review;
import org.example.finalprojectmyshop.product.repository.ReviewRepository;
import org.example.finalprojectmyshop.product.service.ProductService;
import org.example.finalprojectmyshop.product.service.RatingService;
import org.example.finalprojectmyshop.product.service.ReviewService;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.service.UserService;
import org.example.finalprojectmyshop.user.service.impl.UserHelperService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductService productService;
    private final RatingService ratingService;
    private final UserService userService;
    private final UserHelperService userHelperService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductService productService, RatingService ratingService, UserService userService, UserHelperService userHelperService) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
        this.ratingService = ratingService;
        this.userService = userService;
        this.userHelperService = userHelperService;
    }

    @Override
    public void save(AddReviewDTO addReviewDTO, long id) {
        Review review = new Review();

        review.setTitle(addReviewDTO.getTitle());

        Rating rating = new Rating();
        rating.setRating(addReviewDTO.getRating());
        this.ratingService.save(rating);

        review.setRating(rating);
        review.setDescription(addReviewDTO.getDescription());
        review.setDate(new Date());

        UserEntity user = this.userHelperService.getUser();
        review.setUser(user);

        Product product = this.productService.findProductEntityById(id);
        review.setProduct(product);

        this.reviewRepository.save(review);

        user.getReviews().add(review);
        this.userService.save(user);
    }

    @Override
    public Set<ReviewDTO> findAll() {
        UserEntity user = this.userHelperService.getUser();
        Set<Review> reviews = user.getReviews();
        Set<ReviewDTO> reviewDTOS = new HashSet<>();

        reviews.forEach(review -> {
            ReviewDTO reviewDTO = new ReviewDTO();

            reviewDTO.setId(review.getId());
            reviewDTO.setTitle(review.getTitle());
            reviewDTO.setRating(review.getRating().getRating());
            reviewDTO.setDescription(review.getDescription());
            reviewDTO.setDate(review.getDate());

            ReviewUserDTO reviewUserDTO = this.toReviewUserDTO(review.getUser());
            reviewDTO.setUser(reviewUserDTO);

            ReviewProductDTO reviewProductDTO = this.toReviewProductDTO(review.getProduct());
            reviewDTO.setProduct(reviewProductDTO);

            reviewDTOS.add(reviewDTO);
        });

        return reviewDTOS;
    }

    @Override
    public void deleteReview(long id) {
        Optional<Review> optional = this.reviewRepository.findById(id);

        if (optional.isPresent()) {
            Review review = optional.get();
            UserEntity user = this.userHelperService.getUser();
            Product product = review.getProduct();

            user.getReviews().remove(review);
            this.userService.save(user);

            product.getReviews().remove(review);
            this.productService.save(product);

            this.reviewRepository.delete(review);
        }
    }

    @Override
    public void deleteReview(Review review) {
        UserEntity user = review.getUser();
        Product product = review.getProduct();

        user.getReviews().remove(review);
        this.userService.save(user);

        product.getReviews().remove(review);
        this.productService.save(product);

        this.reviewRepository.delete(review);
    }

    private ReviewProductDTO toReviewProductDTO(Product product) {
        ReviewProductDTO reviewProductDTO = new ReviewProductDTO();

        reviewProductDTO.setId(product.getId());
        reviewProductDTO.setName(product.getName());
        reviewProductDTO.setImageUrl(product.getMainImage().getImageUrl());

        return reviewProductDTO;
    }

    private ReviewUserDTO toReviewUserDTO(UserEntity user) {
        ReviewUserDTO reviewUserDTO = new ReviewUserDTO();

        reviewUserDTO.setFirstName(user.getFirstName());
        reviewUserDTO.setLastName(user.getLastName());
        reviewUserDTO.setProfilePictureUrl(user.getProfilePicture().getImageUrl());

        return reviewUserDTO;
    }


}
