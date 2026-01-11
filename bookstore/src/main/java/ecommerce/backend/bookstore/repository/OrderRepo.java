package ecommerce.backend.bookstore.repository;

import ecommerce.backend.bookstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}