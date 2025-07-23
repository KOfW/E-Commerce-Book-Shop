package ecommerce.backend.bookstore.repository;

import ecommerce.backend.bookstore.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
