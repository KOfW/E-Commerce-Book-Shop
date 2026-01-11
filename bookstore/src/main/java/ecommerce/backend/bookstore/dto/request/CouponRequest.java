package ecommerce.backend.bookstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponRequest {
    private String code;
    private Double discountPercent;
    private Integer max_usage;
    private Date startDate;
    private Date endDate;
    private Long cartSessionId;
}
