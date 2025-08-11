package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.ProductRequest;
import ecommerce.backend.bookstore.dto.response.ProductResponse;
import ecommerce.backend.bookstore.entity.Author;
import ecommerce.backend.bookstore.entity.Category;
import ecommerce.backend.bookstore.entity.Discount;
import ecommerce.backend.bookstore.entity.Product;
import ecommerce.backend.bookstore.repository.AuthorRepo;
import ecommerce.backend.bookstore.repository.CategoryRepo;
import ecommerce.backend.bookstore.repository.DiscountRepo;
import ecommerce.backend.bookstore.repository.ProductRepo;
import ecommerce.backend.bookstore.utils.ProductQuantityStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toEntity(ProductRequest request, Author author, Discount discount, Category category) {
        return Product.builder()
                .author(author)
                .desc(request.getDesc())
                .category(category)
                .discount(discount)
                .name(request.getName())
                .price(request.getPrice())
                .quantity_sold(0)
                .status(ProductQuantityStatus.Available)
                .publisher(request.getPublisher())
                .hiddenProduct(true)
                .build();
    }

    public ProductResponse toDTO(Product product) {
        return ProductResponse.builder()
                .price(product.getPrice())
                .desc(product.getDesc())
                .hiddenProduct(product.getHiddenProduct())
                .name(product.getName())
                .quantity_sold(product.getQuantity_sold())
                .publisher(product.getPublisher())
                .author(product.getAuthor() != null ? product.getAuthor().getId() : null)
                .category(product.getCategory() != null ? product.getCategory().getId() : null)
                .discount(product.getDiscount() != null ? product.getDiscount().getId() : null)
                .build();
    }

    public void toUpdate(Product product, ProductRequest request, Author author, Discount discount, Category category) {
        product.setAuthor(author);
        product.setDesc(request.getDesc());
        product.setCategory(category);
        product.setDiscount(discount);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setPublisher(request.getPublisher());
        product.setHiddenProduct(request.getHiddenProduct());
    }
}
