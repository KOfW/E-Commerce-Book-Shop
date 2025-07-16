package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.ProductImageRequest;
import ecommerce.backend.bookstore.dto.response.ProductImageResponse;
import ecommerce.backend.bookstore.entity.Product;
import ecommerce.backend.bookstore.entity.ProductImage;
import ecommerce.backend.bookstore.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImageMapper {

    @Autowired
    private ProductRepo productRepo;

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
}
