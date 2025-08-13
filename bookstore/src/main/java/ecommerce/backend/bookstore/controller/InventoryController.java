package ecommerce.backend.bookstore.controller;

import ecommerce.backend.bookstore.dto.ApiResponse;
import ecommerce.backend.bookstore.dto.request.InventoryRequest;
import ecommerce.backend.bookstore.dto.response.InventoryResponse;
import ecommerce.backend.bookstore.exception.DuplicateProductException;
import ecommerce.backend.bookstore.service.IInventoryService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/inventory")
public class InventoryController {

    private final IInventoryService inventoryService;

    public InventoryController(IInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Get all Inventoryes
    @GetMapping
    public ResponseEntity<ApiResponse<Page<InventoryResponse>>> getAllInventoryes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<InventoryResponse> response = inventoryService.getAll(page, size);
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Inventoryes retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving all Inventoryes", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve Inventoryes", null)
            );
        }
    }

    // Get Inventory by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InventoryResponse>> getInventoryById(@PathVariable Long id) {
        try {
            InventoryResponse response = inventoryService.getEntityById(id);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "Inventory not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Inventory retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving Inventory with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve Inventory", null)
            );
        }
    }

    // Create Inventory
    @PostMapping
    public ResponseEntity<ApiResponse<InventoryResponse>> createInventory(
            @RequestBody @Valid InventoryRequest request) {
        try {
            InventoryResponse response = inventoryService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>("201", "Inventory created successfully", response)
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
            log.error("Error creating Inventory", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to create Inventory", null)
            );
        }
    }

    // Update Inventory
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<InventoryResponse>> updateInventory(
            @PathVariable Long id,
            @RequestBody @Valid InventoryRequest request) {
        try {
            InventoryResponse response = inventoryService.update(id, request);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "Inventory not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Inventory updated successfully", response)
            );
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>("400", e.getMessage(), null)
            );
        } catch (Exception e) {
            log.error("Error updating Inventory with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to update Inventory", null)
            );
        }
    }

    // Delete Inventory
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteInventory(@PathVariable Long id) {
        try {
            boolean deleted = inventoryService.delete(id);
            if (deleted) {
                return ResponseEntity.ok(
                        new ApiResponse<>("200", "Inventory deleted successfully", null)
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>("404", "Inventory not found", null)
            );
        } catch (Exception e) {
            log.error("Error deleting Inventory with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to delete Inventory", null)
            );
        }
    }
}


