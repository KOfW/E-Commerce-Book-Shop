package ecommerce.backend.bookstore.controller;

import ecommerce.backend.bookstore.dto.ApiResponse;
import ecommerce.backend.bookstore.dto.response.AddressResponse;
import ecommerce.backend.bookstore.dto.response.ProductResponse;
import ecommerce.backend.bookstore.service.IAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Page<AddressResponse>>> getAllAddress(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<AddressResponse> response = addressService.getAll(page, size);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<Page<AddressResponse>>(
                            "OK", "DISPLAY ALL PRODUCT SUCCESSFULLY", response));
        } catch (Exception e) {
            log.error("Error display all products", e);
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse<>( // Trong nhánh catch không cần ép rõ vì null → T có thể là gì cũng được
                            "ERROR", "Failed to display all products: " + e.getMessage(), null));
        }
    }
}
