package org.example.finalprojectmyshop.order.repository;

import org.example.finalprojectmyshop.order.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
