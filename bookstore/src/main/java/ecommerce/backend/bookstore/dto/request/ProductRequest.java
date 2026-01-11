package ecommerce.backend.bookstore.dto.request;

import ecommerce.backend.bookstore.entity.Author;
import ecommerce.backend.bookstore.entity.Category;
import ecommerce.backend.bookstore.entity.Discount;
import ecommerce.backend.bookstore.utils.ProductQuantityStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String desc;
    @NotBlank
    @PositiveOrZero
    private Double price;
    @NotBlank
    private String publisher;

    private Boolean hiddenProduct;
    @NotBlank
    private Long categoryId;
    @NotBlank
    private Long authorId;
    private Long discountId;
}
