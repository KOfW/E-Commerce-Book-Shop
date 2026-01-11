package ecommerce.backend.bookstore.repository;

import ecommerce.backend.bookstore.entity.CartItem;
import ecommerce.backend.bookstore.entity.CouponUsage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponUsageRepo extends JpaRepository<CouponUsage, Long> {
}
