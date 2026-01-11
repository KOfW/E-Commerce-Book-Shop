package ecommerce.backend.bookstore.controller;

import ecommerce.backend.bookstore.dto.ApiResponse;
import ecommerce.backend.bookstore.dto.request.PaymentMethodRequest;
import ecommerce.backend.bookstore.dto.response.PaymentMethodResponse;
import ecommerce.backend.bookstore.exception.DuplicateProductException;
import ecommerce.backend.bookstore.service.IPaymentMethodService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/paymentMethod")
public class PaymentMethodController {

    private final IPaymentMethodService paymentMethodService;

    public PaymentMethodController(IPaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    // Get all PaymentMethodes
    @GetMapping
    public ResponseEntity<ApiResponse<Page<PaymentMethodResponse>>> getAllPaymentMethodes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<PaymentMethodResponse> response = paymentMethodService.getAll(page, size);
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "PaymentMethodes retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving all PaymentMethodes", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve PaymentMethodes", null)
            );
        }
    }

    // Get PaymentMethod by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentMethodResponse>> getPaymentMethodById(@PathVariable Long id) {
        try {
            PaymentMethodResponse response = paymentMethodService.getEntityById(id);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "PaymentMethod not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "PaymentMethod retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving PaymentMethod with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve PaymentMethod", null)
            );
        }
    }

    // Create PaymentMethod
    @PostMapping
    public ResponseEntity<ApiResponse<PaymentMethodResponse>> createPaymentMethod(
            @RequestBody @Valid PaymentMethodRequest request) {
        try {
            PaymentMethodResponse response = paymentMethodService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>("201", "PaymentMethod created successfully", response)
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
            log.error("Error creating PaymentMethod", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to create PaymentMethod", null)
            );
        }
    }

    // Update PaymentMethod
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentMethodResponse>> updatePaymentMethod(
            @PathVariable Long id,
            @RequestBody @Valid PaymentMethodRequest request) {
        try {
            PaymentMethodResponse response = paymentMethodService.update(id, request);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "PaymentMethod not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "PaymentMethod updated successfully", response)
            );
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>("400", e.getMessage(), null)
            );
        } catch (Exception e) {
            log.error("Error updating PaymentMethod with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to update PaymentMethod", null)
            );
        }
    }

    // Delete PaymentMethod
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePaymentMethod(@PathVariable Long id) {
        try {
            boolean deleted = paymentMethodService.delete(id);
            if (deleted) {
                return ResponseEntity.ok(
                        new ApiResponse<>("200", "PaymentMethod deleted successfully", null)
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>("404", "PaymentMethod not found", null)
            );
        } catch (Exception e) {
            log.error("Error deleting PaymentMethod with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to delete PaymentMethod", null)
            );
        }
    }
}


