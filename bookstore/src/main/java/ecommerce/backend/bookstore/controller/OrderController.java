package ecommerce.backend.bookstore.controller;

import ecommerce.backend.bookstore.dto.ApiResponse;
import ecommerce.backend.bookstore.dto.request.OrderRequest;
import ecommerce.backend.bookstore.dto.response.OrderResponse;
import ecommerce.backend.bookstore.exception.DuplicateProductException;
import ecommerce.backend.bookstore.service.IOrderSerivce;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/order")
public class OrderController {

    private final IOrderSerivce orderService;

    public OrderController(IOrderSerivce orderService) {
        this.orderService = orderService;
    }

    // Get all Orderes
    @GetMapping
    public ResponseEntity<ApiResponse<Page<OrderResponse>>> getAllOrderes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<OrderResponse> response = orderService.getAll(page, size);
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Orderes retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving all Orderes", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve Orderes", null)
            );
        }
    }

    // Get Order by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(@PathVariable Long id) {
        try {
            OrderResponse response = orderService.getEntityById(id);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "Order not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Order retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving Order with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve Order", null)
            );
        }
    }

    // Create Order
    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(
            @RequestBody @Valid OrderRequest request) {
        try {
            OrderResponse response = orderService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>("201", "Order created successfully", response)
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
            log.error("Error creating Order", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to create Order", null)
            );
        }
    }

    // Update Order
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> updateOrder(
            @PathVariable Long id,
            @RequestBody @Valid OrderRequest request) {
        try {
            OrderResponse response = orderService.update(id, request);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "Order not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Order updated successfully", response)
            );
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>("400", e.getMessage(), null)
            );
        } catch (Exception e) {
            log.error("Error updating Order with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to update Order", null)
            );
        }
    }

    // Delete Order
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteOrder(@PathVariable Long id) {
        try {
            boolean deleted = orderService.delete(id);
            if (deleted) {
                return ResponseEntity.ok(
                        new ApiResponse<>("200", "Order deleted successfully", null)
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>("404", "Order not found", null)
            );
        } catch (Exception e) {
            log.error("Error deleting Order with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to delete Order", null)
            );
        }
    }
}


