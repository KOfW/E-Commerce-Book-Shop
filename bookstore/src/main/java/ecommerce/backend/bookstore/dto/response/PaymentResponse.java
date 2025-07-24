package ecommerce.backend.bookstore.dto.response;

import ecommerce.backend.bookstore.utils.PaymentStatus;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private Long id;
    private Double total;
    private PaymentStatus status;
    private Date paymentDate;
    private Long paymentMethodId;
    private Long userId;
    private Long orderId;
}
