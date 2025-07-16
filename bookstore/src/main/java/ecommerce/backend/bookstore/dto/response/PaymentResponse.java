package ecommerce.backend.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private Long id;
    private Double total;
    private String status;
    private Date paymentDate;
    private Long paymentMethodId;
    private Long userId;
    private Long orderId;
}
