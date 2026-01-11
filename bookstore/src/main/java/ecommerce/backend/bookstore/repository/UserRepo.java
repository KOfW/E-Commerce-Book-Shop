package ecommerce.backend.bookstore.repository;

import ecommerce.backend.bookstore.entity.CartItem;
import ecommerce.backend.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
