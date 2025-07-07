package ecommerce.backend.bookstore.controller;

import ecommerce.backend.bookstore.dto.ApiResponse;
import ecommerce.backend.bookstore.dto.request.ProductRequest;
import ecommerce.backend.bookstore.dto.response.ProductResponse;
import ecommerce.backend.bookstore.exception.DuplicateProductException;
import ecommerce.backend.bookstore.service.IProductService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Page<ProductResponse>>> getAllProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<ProductResponse> response = productService.getAll(page, size);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<Page<ProductResponse>>(
                            "OK", "DISPLAY ALL PRODUCT SUCCESSFULLY", response));
        } catch (Exception e) {
            log.error("Error display all products", e);
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse<>( // Trong nhánh catch không cần ép rõ vì null → T có thể là gì cũng được
                            "ERROR", "Failed to display all products: " + e.getMessage(), null));
        }
    }


    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(
            @RequestBody @Valid ProductRequest request) {
        try {
            ProductResponse response = productService.create(request);
            return ResponseEntity.ok(
                    new ApiResponse<ProductResponse>("SUCCESS", "Product created successfully", response));

        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<ProductResponse>("VALIDATION_ERROR", e.getMessage(), null));

        } catch (DuplicateProductException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ApiResponse<ProductResponse>("CONFLICT", e.getMessage(), null));

        } catch (Exception e) {
            log.error("Error creating product", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<ProductResponse>("ERROR", "Failed to create product: " + e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteProduct(@PathVariable Long id) {
        try {
            boolean response = productService.delete(id);
            if(response){
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<Boolean>("OK", "Product deleted successfully", response));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<Boolean>("FAILED", "Failed to delete product: ", response));
        } catch (Exception e) {
            log.error("Error creating product", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("ERROR", "Failed to create product: " + e.getMessage(), null));
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(@RequestBody @Valid ProductRequest request ,@PathVariable Long id){
        try {
            ProductResponse response = productService.update(request, id);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<ProductResponse>("OK","UPDATE PRODUCT SUCCESSFULLY",response));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<ProductResponse>("VALIDATION_ERROR",e.getMessage(),null));
        } catch (Exception e){
            log.error("Error update product", e);
            return ResponseEntity.internalServerError().body(new ApiResponse<ProductResponse>("ERROR","FALED TO CREATE PRODUCT"+e.getMessage(),null));
        }
    }
}

