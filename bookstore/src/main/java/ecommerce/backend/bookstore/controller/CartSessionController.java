package ecommerce.backend.bookstore.controller;

import ecommerce.backend.bookstore.dto.ApiResponse;
import ecommerce.backend.bookstore.dto.request.CartSessionRequest;
import ecommerce.backend.bookstore.dto.response.CartSessionResponse;
import ecommerce.backend.bookstore.exception.DuplicateProductException;
import ecommerce.backend.bookstore.service.ICartSessionService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/cartSession")
public class CartSessionController {

    private final ICartSessionService cartSessionService;

    public CartSessionController(ICartSessionService cartSessionService) {
        this.cartSessionService = cartSessionService;
    }

    // Get all CartSessiones
    @GetMapping
    public ResponseEntity<ApiResponse<Page<CartSessionResponse>>> getAllCartSessiones(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<CartSessionResponse> response = cartSessionService.getAll(page, size);
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "CartSessiones retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving all CartSessiones", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve CartSessiones", null)
            );
        }
    }

    // Get CartSession by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CartSessionResponse>> getCartSessionById(@PathVariable Long id) {
        try {
            CartSessionResponse response = cartSessionService.getEntityById(id);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "CartSession not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "CartSession retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving CartSession with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve CartSession", null)
            );
        }
    }

    // Create CartSession
    @PostMapping
    public ResponseEntity<ApiResponse<CartSessionResponse>> createCartSession(
            @RequestBody @Valid CartSessionRequest request) {
        try {
            CartSessionResponse response = cartSessionService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>("201", "CartSession created successfully", response)
            );
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>("400", e.getMessage(), null)
            );
        } catch (DuplicateProductException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ApiResponse<>("409", e.getMessage(), null)
            );
        } catch (Exception e) {
            log.error("Error creating CartSession", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to create CartSession", null)
            );
        }
    }

    // Update CartSession
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CartSessionResponse>> updateCartSession(
            @PathVariable Long id,
            @RequestBody @Valid CartSessionRequest request) {
        try {
            CartSessionResponse response = cartSessionService.update(id, request);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "CartSession not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "CartSession updated successfully", response)
            );
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>("400", e.getMessage(), null)
            );
        } catch (Exception e) {
            log.error("Error updating CartSession with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to update CartSession", null)
            );
        }
    }

    // Delete CartSession
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCartSession(@PathVariable Long id) {
        try {
            boolean deleted = cartSessionService.delete(id);
            if (deleted) {
                return ResponseEntity.ok(
                        new ApiResponse<>("200", "CartSession deleted successfully", null)
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>("404", "CartSession not found", null)
            );
        } catch (Exception e) {
            log.error("Error deleting CartSession with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to delete CartSession", null)
            );
        }
    }
}


