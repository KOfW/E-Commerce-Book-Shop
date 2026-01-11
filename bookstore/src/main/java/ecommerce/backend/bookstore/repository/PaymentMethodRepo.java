package ecommerce.backend.bookstore.repository;

import ecommerce.backend.bookstore.entity.CartItem;
import ecommerce.backend.bookstore.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepo extends JpaRepository<PaymentMethod, Long> {
}
