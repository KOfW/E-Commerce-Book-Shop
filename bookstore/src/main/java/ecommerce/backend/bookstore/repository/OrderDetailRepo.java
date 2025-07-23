package ecommerce.backend.bookstore.repository;

import ecommerce.backend.bookstore.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {
}
