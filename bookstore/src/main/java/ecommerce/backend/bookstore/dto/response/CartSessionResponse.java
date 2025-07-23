package ecommerce.backend.bookstore.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartSessionResponse {
    private Long id;
    private Double total;
    private Long userId;
}
