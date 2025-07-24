package ecommerce.backend.bookstore.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentMethodResponse {
    private Long id;
    private String methodCode;
    private String methodName;
    private String description;
}
