package ecommerce.backend.bookstore.dto.request;

import ecommerce.backend.bookstore.entity.*;
import ecommerce.backend.bookstore.utils.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String email;
    private String passwordHash;
    private String phone;
    private String firstName;
    private String lastName;
    private String urlImage;
    private UserStatus active;
}
