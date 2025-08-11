package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.ProductImageRequest;
import ecommerce.backend.bookstore.dto.response.ProductImageResponse;
import ecommerce.backend.bookstore.entity.Product;
import ecommerce.backend.bookstore.entity.ProductImage;
import ecommerce.backend.bookstore.repository.ProductImageRepo;
import ecommerce.backend.bookstore.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImageMapper {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductImageRepo productImageRepo;

    public ProductImage toEntity(ProductImageRequest request) {

        Product product = productRepo.getProductsById(request.getProductId());

        return ProductImage.builder()
                .name(request.getName())
                .product(product)
                .build();
    }

    public ProductImageResponse toDTO(ProductImage productImage){

        ProductImageResponse response = new ProductImageResponse();
        response.setId(productImage.getId());
        response.setUrl(productImage.getImageUrl());
        response.setProductId(productImage.getProduct().getId());

        return response;
    }

    public void toUpdate(ProductImage entity, ProductImageRequest request) {
        // Update the entity with request values
        entity.setName(request.getName());
        entity.setProduct(productRepo.findById(request.getProductId()).orElseThrow(() -> new RuntimeException("not found product")));
    }
}
