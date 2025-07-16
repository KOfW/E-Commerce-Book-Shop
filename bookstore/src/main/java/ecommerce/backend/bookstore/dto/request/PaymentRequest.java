package ecommerce.backend.bookstore.dto.request;

import ecommerce.backend.bookstore.entity.Order;
import ecommerce.backend.bookstore.entity.PaymentMethod;
import ecommerce.backend.bookstore.entity.User;
import ecommerce.backend.bookstore.utils.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private Double total;
    private String status;
    private Date paymentDate;
    private Long paymentMethodId;
    private Long userId;
    private Long orderId;
}
