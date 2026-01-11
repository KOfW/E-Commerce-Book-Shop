package ecommerce.backend.bookstore.dto.request;

import ecommerce.backend.bookstore.entity.Coupon;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponUsageRequest {
    private Integer usageAmount;
    private Long couponId;
}
