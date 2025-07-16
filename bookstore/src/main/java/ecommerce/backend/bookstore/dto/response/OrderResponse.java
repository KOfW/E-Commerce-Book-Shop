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
public class OrderResponse {
    private Long id;
    private Double total;
    private String status;
    private Date orderDate;
    private Long userId;
    private Long shippingAddressId;
}
