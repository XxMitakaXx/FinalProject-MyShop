package org.example.finalprojectmyshop.order.repository;

import org.example.finalprojectmyshop.order.models.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
}
