package ecommerce.backend.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_method")
@Builder
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String methodCode; // Ví dụ: COD, MOMO, BANK

    private String methodName; // Ví dụ: Thanh toán khi nhận hàng

    private String description;
}
