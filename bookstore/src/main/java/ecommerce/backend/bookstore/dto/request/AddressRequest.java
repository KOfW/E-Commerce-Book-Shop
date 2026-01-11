package ecommerce.backend.bookstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    @NotBlank(message = "street không được để trống")
    private String street;
    @NotBlank(message = "city không được để trống")
    private String city;
    private String province;
    private String country;
    private Long userId;
}
