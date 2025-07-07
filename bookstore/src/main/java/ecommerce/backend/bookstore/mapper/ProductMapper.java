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

    private CategoryRepo categoryRepo;
    private DiscountRepo discountRepo;
    private AuthorRepo authorRepo;
    private ProductRepo productRepo;

    public Product toEntity(ProductRequest request){

        Author author = authorRepo.getById(request.getAuthorId());
        Discount discount = discountRepo.getById(request.getDiscountId());
        Category category = categoryRepo.getById(request.getCategoryId());

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

    public ProductResponse toDTO(Product product){
        return ProductResponse.builder()
                .price(product.getPrice())
                .desc(product.getDesc())
                .hiddenProduct(product.getHiddenProduct())
                .name(product.getName())
                .quantity_sold(product.getQuantity_sold())
                .publisher(product.getPublisher())
                .author(product.getAuthor().getId())
                .category(product.getCategory().getId())
                .discount(product.getDiscount().getId())
                .build();
    }

    public ProductResponse update(ProductRequest request, Product product) {
        Author author = authorRepo.getById(request.getAuthorId());
        Discount discount = discountRepo.getById(request.getDiscountId());
        Category category = categoryRepo.getById(request.getCategoryId());

        product.setAuthor(author);
        product.setDesc(request.getDesc());
        product.setCategory(category);
        product.setDiscount(discount);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStatus(ProductQuantityStatus.Available);
        product.setPublisher(request.getPublisher());
        product.setHiddenProduct(request.getHiddenProduct()); // ép về true luôn

        // Gọi lại quantity_sold nếu cần tính lại (chỉ khi product.getId() != null)
        if (product.getId() != null) {
            product.setQuantity_sold(productRepo.quantitySold(product.getId()));
        }

        productRepo.save(product);

        return toDTO(product); // dùng lại method toDTO để map response
    }
}
