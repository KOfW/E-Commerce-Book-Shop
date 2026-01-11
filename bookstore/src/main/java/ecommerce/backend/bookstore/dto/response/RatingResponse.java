package ecommerce.backend.bookstore.dto.response;

import ecommerce.backend.bookstore.entity.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingResponse {
    private Long id;
    private Long parentId;
    private Double ratingNumber;
    private Boolean isVerified;
    private Long userId;
}
