package ecommerce.backend.bookstore.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {
    private Long id;
    private String street;
    private String city;
    private String province;
    private String country;
    private Long userId;
}
