package org.example.finalprojectmyshop.product.repository;

import org.example.finalprojectmyshop.product.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("FROM Product p WHERE p.name LIKE %:name%")
    Set<Product> findProductsByName(String name);
}
