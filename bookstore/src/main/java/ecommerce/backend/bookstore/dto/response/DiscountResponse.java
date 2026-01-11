package ecommerce.backend.bookstore.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscountResponse {
    private Long id;
    private Double discountPercent;
    private Date startDate;
    private Date endDate;
}
