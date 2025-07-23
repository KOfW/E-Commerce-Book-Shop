package ecommerce.backend.bookstore.dto.request;

import ecommerce.backend.bookstore.utils.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Double total;
    private Date orderDate;
    private Long userId;
    private Long shippingAddressId;

}
