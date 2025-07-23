package ecommerce.backend.bookstore.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private Double total;
    private String status;
    private Date orderDate;
    private Long userId;
    private Long shippingAddressId;
}
