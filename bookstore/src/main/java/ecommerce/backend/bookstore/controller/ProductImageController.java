package ecommerce.backend.bookstore.controller;

import ecommerce.backend.bookstore.dto.ApiResponse;
import ecommerce.backend.bookstore.dto.request.ProductImageRequest;
import ecommerce.backend.bookstore.dto.request.ProductRequest;
import ecommerce.backend.bookstore.dto.response.ProductImageResponse;
import ecommerce.backend.bookstore.dto.response.ProductResponse;
import ecommerce.backend.bookstore.exception.DuplicateProductException;
import ecommerce.backend.bookstore.service.IProductImageService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/image")
public class ProductImageController {

    @Autowired
    private IProductImageService productImageService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<ProductImageResponse>> uploadImage(
            @RequestPart("request") @Valid ProductImageRequest request,
            @RequestPart("file") MultipartFile file) {
        try {
            ProductImageResponse response = productImageService.uploadImage(request, file);
            return ResponseEntity.ok(
                    new ApiResponse<ProductImageResponse>("SUCCESS", "Product created successfully", response));

        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<ProductImageResponse>("VALIDATION_ERROR", e.getMessage(), null));

        } catch (DuplicateProductException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ApiResponse<ProductImageResponse>("CONFLICT", e.getMessage(), null));

        } catch (Exception e) {
            log.error("Error creating product", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<ProductImageResponse>("ERROR", "Failed to create product: " + e.getMessage(), null));
        }
    }
}
