package ecommerce.backend.bookstore.repository;

import ecommerce.backend.bookstore.entity.CartItem;
import ecommerce.backend.bookstore.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepo extends JpaRepository<Rating, Long> {
}
