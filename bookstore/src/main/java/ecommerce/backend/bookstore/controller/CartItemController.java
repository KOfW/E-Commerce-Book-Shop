package ecommerce.backend.bookstore.controller;

import ecommerce.backend.bookstore.dto.ApiResponse;
import ecommerce.backend.bookstore.dto.request.CartItemRequest;
import ecommerce.backend.bookstore.dto.response.CartItemResponse;
import ecommerce.backend.bookstore.exception.DuplicateProductException;
import ecommerce.backend.bookstore.service.ICartItemService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/cartItem")
public class CartItemController {

    private final ICartItemService cartItemService;

    public CartItemController(ICartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    // Get all CartItemes
    @GetMapping
    public ResponseEntity<ApiResponse<Page<CartItemResponse>>> getAllCartItemes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<CartItemResponse> response = cartItemService.getAll(page, size);
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "CartItemes retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving all CartItemes", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve CartItemes", null)
            );
        }
    }

    // Get CartItem by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CartItemResponse>> getCartItemById(@PathVariable Long id) {
        try {
            CartItemResponse response = cartItemService.getEntityById(id);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "CartItem not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "CartItem retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving CartItem with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve CartItem", null)
            );
        }
    }

    // Create CartItem
    @PostMapping
    public ResponseEntity<ApiResponse<CartItemResponse>> createCartItem(
            @RequestBody @Valid CartItemRequest request) {
        try {
            CartItemResponse response = cartItemService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>("201", "CartItem created successfully", response)
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
            log.error("Error creating CartItem", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to create CartItem", null)
            );
        }
    }

    // Update CartItem
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CartItemResponse>> updateCartItem(
            @PathVariable Long id,
            @RequestBody @Valid CartItemRequest request) {
        try {
            CartItemResponse response = cartItemService.update(id, request);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "CartItem not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "CartItem updated successfully", response)
            );
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>("400", e.getMessage(), null)
            );
        } catch (Exception e) {
            log.error("Error updating CartItem with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to update CartItem", null)
            );
        }
    }

    // Delete CartItem
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCartItem(@PathVariable Long id) {
        try {
            boolean deleted = cartItemService.delete(id);
            if (deleted) {
                return ResponseEntity.ok(
                        new ApiResponse<>("200", "CartItem deleted successfully", null)
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>("404", "CartItem not found", null)
            );
        } catch (Exception e) {
            log.error("Error deleting CartItem with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to delete CartItem", null)
            );
        }
    }
}

