package ecommerce.backend.bookstore.dto.response;

import ecommerce.backend.bookstore.entity.CartItem;
import ecommerce.backend.bookstore.entity.Inventory;
import ecommerce.backend.bookstore.entity.ProductImage;
import ecommerce.backend.bookstore.entity.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String name;
    private String desc;
    private Double price;
    private Integer quantity_sold;
    private String publisher;
    private Boolean hiddenProduct;
    private Long category;
    private Long author;
    private Long discount;

    private String status;
}
