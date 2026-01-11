package ecommerce.backend.bookstore.repository;

import ecommerce.backend.bookstore.dto.request.ProductRequest;
import ecommerce.backend.bookstore.dto.response.ProductResponse;
import ecommerce.backend.bookstore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepo extends JpaRepository<Product, Long> {
    public Product getProductsById(Long id);

    @Query ("SELECT SUM(od.amount) FROM OrderDetail od WHERE od.product.id = :id")
    public int quantitySold(Long id);
}
