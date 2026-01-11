package ecommerce.backend.bookstore.dto.response;

import ecommerce.backend.bookstore.utils.UserStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String passwordHash;
    private String phone;
    private String firstName;
    private String lastName;
    private String urlImage;
    private UserStatus active;
}
