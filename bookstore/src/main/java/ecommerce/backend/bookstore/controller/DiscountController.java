package ecommerce.backend.bookstore.controller;

import ecommerce.backend.bookstore.dto.ApiResponse;
import ecommerce.backend.bookstore.dto.request.DiscountRequest;
import ecommerce.backend.bookstore.dto.response.DiscountResponse;
import ecommerce.backend.bookstore.exception.DuplicateProductException;
import ecommerce.backend.bookstore.service.IDiscountService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/Discount")
public class DiscountController {

    private final IDiscountService discountService;

    public DiscountController(IDiscountService discountService) {
        this.discountService = discountService;
    }

    // Get all Discountes
    @GetMapping
    public ResponseEntity<ApiResponse<Page<DiscountResponse>>> getAllDiscountes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<DiscountResponse> response = discountService.getAll(page, size);
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Discountes retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving all Discountes", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve Discountes", null)
            );
        }
    }

    // Get Discount by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DiscountResponse>> getDiscountById(@PathVariable Long id) {
        try {
            DiscountResponse response = discountService.getEntityById(id);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "Discount not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Discount retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving Discount with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve Discount", null)
            );
        }
    }

    // Create Discount
    @PostMapping
    public ResponseEntity<ApiResponse<DiscountResponse>> createDiscount(
            @RequestBody @Valid DiscountRequest request) {
        try {
            DiscountResponse response = discountService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>("201", "Discount created successfully", response)
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
            log.error("Error creating Discount", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to create Discount", null)
            );
        }
    }

    // Update Discount
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DiscountResponse>> updateDiscount(
            @PathVariable Long id,
            @RequestBody @Valid DiscountRequest request) {
        try {
            DiscountResponse response = discountService.update(id, request);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "Discount not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Discount updated successfully", response)
            );
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>("400", e.getMessage(), null)
            );
        } catch (Exception e) {
            log.error("Error updating Discount with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to update Discount", null)
            );
        }
    }

    // Delete Discount
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDiscount(@PathVariable Long id) {
        try {
            boolean deleted = discountService.delete(id);
            if (deleted) {
                return ResponseEntity.ok(
                        new ApiResponse<>("200", "Discount deleted successfully", null)
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>("404", "Discount not found", null)
            );
        } catch (Exception e) {
            log.error("Error deleting Discount with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to delete Discount", null)
            );
        }
    }
}


