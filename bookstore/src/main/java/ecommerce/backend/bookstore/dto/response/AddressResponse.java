package ecommerce.backend.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private Long id;
    private String street;
    private String city;
    private String province;
    private String country;
    private Long userId;
}
