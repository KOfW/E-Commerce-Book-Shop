package ecommerce.backend.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {
    private Long id;
    private Integer amount;
    private Double total;
    private Long cartSessionId;
    private Long productId;
}
