package ecommerce.backend.bookstore.repository;

import ecommerce.backend.bookstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    public Category getById(Long id);
}
