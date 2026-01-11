package ecommerce.backend.bookstore.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {
    private Long id;
    private Integer amount;
    private Double total;
    private Long orderId;
    private Long productId;
}
