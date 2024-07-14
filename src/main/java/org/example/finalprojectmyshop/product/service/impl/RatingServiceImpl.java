package org.example.finalprojectmyshop.product.service.impl;

import org.example.finalprojectmyshop.product.models.entities.Rating;
import org.example.finalprojectmyshop.product.repository.RatingRepository;
import org.example.finalprojectmyshop.product.service.RatingService;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public void save(Rating rating) {
        this.ratingRepository.save(rating);
    }
}
