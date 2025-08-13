package ecommerce.backend.bookstore.controller;

import ecommerce.backend.bookstore.dto.ApiResponse;
import ecommerce.backend.bookstore.dto.request.AddressRequest;
import ecommerce.backend.bookstore.dto.response.AddressResponse;
import ecommerce.backend.bookstore.exception.DuplicateProductException;
import ecommerce.backend.bookstore.service.IAddressService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/address")
public class AddressController {

    private final IAddressService addressService;

    public AddressController(IAddressService addressService) {
        this.addressService = addressService;
    }

    // Get all addresses
    @GetMapping
    public ResponseEntity<ApiResponse<Page<AddressResponse>>> getAllAddresses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<AddressResponse> response = addressService.getAll(page, size);
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Addresses retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving all addresses", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve addresses", null)
            );
        }
    }

    // Get address by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AddressResponse>> getAddressById(@PathVariable Long id) {
        try {
            AddressResponse response = addressService.getEntityById(id);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "Address not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Address retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving address with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve address", null)
            );
        }
    }

    // Create address
    @PostMapping
    public ResponseEntity<ApiResponse<AddressResponse>> createAddress(
            @RequestBody @Valid AddressRequest request) {
        try {
            AddressResponse response = addressService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>("201", "Address created successfully", response)
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
            log.error("Error creating address", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to create address", null)
            );
        }
    }

    // Update address
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AddressResponse>> updateAddress(
            @PathVariable Long id,
            @RequestBody @Valid AddressRequest request) {
        try {
            AddressResponse response = addressService.update(id, request);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "Address not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Address updated successfully", response)
            );
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>("400", e.getMessage(), null)
            );
        } catch (Exception e) {
            log.error("Error updating address with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to update address", null)
            );
        }
    }

    // Delete address
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAddress(@PathVariable Long id) {
        try {
            boolean deleted = addressService.delete(id);
            if (deleted) {
                return ResponseEntity.ok(
                        new ApiResponse<>("200", "Address deleted successfully", null)
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>("404", "Address not found", null)
            );
        } catch (Exception e) {
            log.error("Error deleting address with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to delete address", null)
            );
        }
    }
}

