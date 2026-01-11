package ecommerce.backend.bookstore.repository;

import ecommerce.backend.bookstore.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepo extends JpaRepository<Discount, Long> {
    public Discount getById(Long id);
}
