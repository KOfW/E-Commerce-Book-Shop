package ecommerce.backend.bookstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountRequest {
    @NotBlank
    private Double discountPercent;
    private Date startDate;
    private Date endDate;
}
