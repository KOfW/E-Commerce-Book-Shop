package ecommerce.backend.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coupon")
@Builder
public class Coupon extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Double discountPercent;
    private Integer max_usage;
    private Date startDate;
    private Date endDate;

    @ManyToMany(mappedBy = "coupons")
    private Set<User> users = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_session_id")
    private CartSession cartSession;

    @OneToOne(mappedBy = "coupon")
    private CouponUsage couponUsage;
}
