package ecommerce.backend.bookstore.repository;

import ecommerce.backend.bookstore.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepo extends JpaRepository<ProductImage, Long> {
    public ProductImage findByProduct_Id(Long id);
}
