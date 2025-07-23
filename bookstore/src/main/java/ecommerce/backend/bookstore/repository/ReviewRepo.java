package ecommerce.backend.bookstore.repository;

import ecommerce.backend.bookstore.entity.CartItem;
import ecommerce.backend.bookstore.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review, Long> {
}
