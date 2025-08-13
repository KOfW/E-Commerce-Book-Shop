package ecommerce.backend.bookstore.controller;

import ecommerce.backend.bookstore.dto.ApiResponse;
import ecommerce.backend.bookstore.dto.request.OrderDetailRequest;
import ecommerce.backend.bookstore.dto.response.OrderDetailResponse;
import ecommerce.backend.bookstore.exception.DuplicateProductException;
import ecommerce.backend.bookstore.service.IOrderDetailService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/orderDetail")
public class OrderDetailController {

    private final IOrderDetailService orderDetailService;

    public OrderDetailController(IOrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    // Get all OrderDetailes
    @GetMapping
    public ResponseEntity<ApiResponse<Page<OrderDetailResponse>>> getAllOrderDetailes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<OrderDetailResponse> response = orderDetailService.getAll(page, size);
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "OrderDetailes retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving all OrderDetailes", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve OrderDetailes", null)
            );
        }
    }

    // Get OrderDetail by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderDetailResponse>> getOrderDetailById(@PathVariable Long id) {
        try {
            OrderDetailResponse response = orderDetailService.getEntityById(id);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "OrderDetail not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "OrderDetail retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving OrderDetail with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve OrderDetail", null)
            );
        }
    }

    // Create OrderDetail
    @PostMapping
    public ResponseEntity<ApiResponse<OrderDetailResponse>> createOrderDetail(
            @RequestBody @Valid OrderDetailRequest request) {
        try {
            OrderDetailResponse response = orderDetailService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>("201", "OrderDetail created successfully", response)
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
            log.error("Error creating OrderDetail", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to create OrderDetail", null)
            );
        }
    }

    // Update OrderDetail
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderDetailResponse>> updateOrderDetail(
            @PathVariable Long id,
            @RequestBody @Valid OrderDetailRequest request) {
        try {
            OrderDetailResponse response = orderDetailService.update(id, request);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "OrderDetail not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "OrderDetail updated successfully", response)
            );
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>("400", e.getMessage(), null)
            );
        } catch (Exception e) {
            log.error("Error updating OrderDetail with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to update OrderDetail", null)
            );
        }
    }

    // Delete OrderDetail
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteOrderDetail(@PathVariable Long id) {
        try {
            boolean deleted = orderDetailService.delete(id);
            if (deleted) {
                return ResponseEntity.ok(
                        new ApiResponse<>("200", "OrderDetail deleted successfully", null)
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>("404", "OrderDetail not found", null)
            );
        } catch (Exception e) {
            log.error("Error deleting OrderDetail with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to delete OrderDetail", null)
            );
        }
    }
}


