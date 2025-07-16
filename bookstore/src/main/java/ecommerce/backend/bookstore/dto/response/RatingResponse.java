package ecommerce.backend.bookstore.dto.response;

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
public class RatingResponse {
    private Long id;
    private Long parentId;
    private Double ratingNumber;
    private Boolean isVerified;
    private Long userId;
}
