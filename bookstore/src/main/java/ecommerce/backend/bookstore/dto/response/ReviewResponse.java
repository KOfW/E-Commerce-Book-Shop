package ecommerce.backend.bookstore.dto.response;

import ecommerce.backend.bookstore.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
    private Long id;
    private Boolean isVerified;
    private String content;
    private Review parent;
    private Long userId;
    private Long productId;
}
