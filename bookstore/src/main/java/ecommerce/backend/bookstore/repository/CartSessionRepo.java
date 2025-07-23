package ecommerce.backend.bookstore.repository;

import ecommerce.backend.bookstore.entity.CartItem;
import ecommerce.backend.bookstore.entity.CartSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartSessionRepo extends JpaRepository<CartSession, Long> {
}
