package org.example.finalprojectmyshop.order.repository;

import org.example.finalprojectmyshop.order.models.entities.ProductInOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInOrderRepository extends JpaRepository<ProductInOrderEntity, Long> {
}
