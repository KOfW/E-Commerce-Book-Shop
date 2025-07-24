package ecommerce.backend.bookstore.dto.response;

import ecommerce.backend.bookstore.entity.Review;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponse {
    private Long id;
    private Boolean isVerified;
    private String content;
    private Review parent;
    private Long userId;
    private Long productId;
}
