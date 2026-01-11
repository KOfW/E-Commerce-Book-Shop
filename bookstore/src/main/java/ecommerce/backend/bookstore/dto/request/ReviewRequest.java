package ecommerce.backend.bookstore.dto.request;

import ecommerce.backend.bookstore.entity.Product;
import ecommerce.backend.bookstore.entity.Review;
import ecommerce.backend.bookstore.entity.User;
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
public class ReviewRequest {
    private Boolean isVerified;
    private String content;
    private Long parentId;
    private Long userId;
    private Long productId;
}
