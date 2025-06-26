package ecommerce.backend.bookstore.entity;

import ecommerce.backend.bookstore.utils.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends Base{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String passwordHash;
    @Column(unique = true, nullable = false)
    private String phone;
    private String firstName;
    private String lastName;
    @Column(name = "image_url")
    private String urlImage;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus active;
}
