package org.example.finalprojectmyshop.product.repository;

import org.example.finalprojectmyshop.product.models.entities.ProductProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPropertyRepository extends JpaRepository<ProductProperty, Long> {
}
