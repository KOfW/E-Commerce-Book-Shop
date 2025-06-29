package ecommerce.backend.bookstore.entity;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "user_role"
                , joinColumns = @JoinColumn(name="user_id")
                , inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> role= new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();
    @ManyToMany
    @JoinTable( name = "coupon_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "coupon_id"))
    private Set<Coupon> coupons = new HashSet<>();
}
