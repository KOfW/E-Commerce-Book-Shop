package ecommerce.backend.bookstore.controller;

import ecommerce.backend.bookstore.dto.ApiResponse;
import ecommerce.backend.bookstore.dto.request.PaymentRequest;
import ecommerce.backend.bookstore.dto.response.PaymentResponse;
import ecommerce.backend.bookstore.exception.DuplicateProductException;
import ecommerce.backend.bookstore.service.IPaymentService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/payment")
public class PaymentController {

    private final IPaymentService paymentService;

    public PaymentController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Get all Paymentes
    @GetMapping
    public ResponseEntity<ApiResponse<Page<PaymentResponse>>> getAllPaymentes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<PaymentResponse> response = paymentService.getAll(page, size);
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Paymentes retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving all Paymentes", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve Paymentes", null)
            );
        }
    }

    // Get Payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentResponse>> getPaymentById(@PathVariable Long id) {
        try {
            PaymentResponse response = paymentService.getEntityById(id);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "Payment not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Payment retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving Payment with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve Payment", null)
            );
        }
    }

    // Create Payment
    @PostMapping
    public ResponseEntity<ApiResponse<PaymentResponse>> createPayment(
            @RequestBody @Valid PaymentRequest request) {
        try {
            PaymentResponse response = paymentService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>("201", "Payment created successfully", response)
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
            log.error("Error creating Payment", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to create Payment", null)
            );
        }
    }

    // Update Payment
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentResponse>> updatePayment(
            @PathVariable Long id,
            @RequestBody @Valid PaymentRequest request) {
        try {
            PaymentResponse response = paymentService.update(id, request);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "Payment not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Payment updated successfully", response)
            );
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>("400", e.getMessage(), null)
            );
        } catch (Exception e) {
            log.error("Error updating Payment with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to update Payment", null)
            );
        }
    }

    // Delete Payment
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePayment(@PathVariable Long id) {
        try {
            boolean deleted = paymentService.delete(id);
            if (deleted) {
                return ResponseEntity.ok(
                        new ApiResponse<>("200", "Payment deleted successfully", null)
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>("404", "Payment not found", null)
            );
        } catch (Exception e) {
            log.error("Error deleting Payment with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to delete Payment", null)
            );
        }
    }
}


