package ecommerce.backend.bookstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    private String street;
    private String city;
    private String province;
    private String country;
    private Long userId;
}
