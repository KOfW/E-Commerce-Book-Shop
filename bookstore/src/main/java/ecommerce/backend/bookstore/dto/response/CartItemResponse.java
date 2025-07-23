package ecommerce.backend.bookstore.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemResponse {
    private Long id;
    private Integer amount;
    private Double total;
    private Long cartSessionId;
    private Long productId;
}
