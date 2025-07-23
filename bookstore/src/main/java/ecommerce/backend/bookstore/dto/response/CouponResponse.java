package ecommerce.backend.bookstore.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponResponse {
    private Long id;
    private String code;
    private Double discountPercent;
    private Integer max_usage;
    private Date startDate;
    private Date endDate;
    private Long cartSessionId;
}
