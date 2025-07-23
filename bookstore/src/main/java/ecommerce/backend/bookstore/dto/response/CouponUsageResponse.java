package ecommerce.backend.bookstore.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponUsageResponse {
    private Long id;
    private Integer usageAmount;
    private Long couponId;
}
