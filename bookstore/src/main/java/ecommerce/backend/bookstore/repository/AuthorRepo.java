package ecommerce.backend.bookstore.repository;

import ecommerce.backend.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {
    public Author getById(Long id);
}
