package ecommerce.backend.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coupon")
public class Coupon extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Double discountPercent;
    private Integer max_usage;
    private Date startDate;
    private Date endDate;

    @ManyToMany(mappedBy = "coupon")
    private Set<User> users = new HashSet<>();
}
