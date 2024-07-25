package org.example.finalprojectmyshop.order.repository;

import org.example.finalprojectmyshop.order.models.entities.ProductInCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInCartRepository extends JpaRepository<ProductInCartEntity, Long> {
}
