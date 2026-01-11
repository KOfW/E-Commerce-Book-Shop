package ecommerce.backend.bookstore.dto.request;

import ecommerce.backend.bookstore.entity.Order;
import ecommerce.backend.bookstore.entity.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRequest {
    private Integer amount;
    private Double total;
    private Long orderId;
    private Long productId;
}
