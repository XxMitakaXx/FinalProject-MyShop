package org.example.finalprojectmyshop.product.repository;

import org.example.finalprojectmyshop.product.models.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("FROM Review r WHERE r.product.id = :productId")
    Set<Review> findByProductId(@RequestParam("productId") long productId);

}
