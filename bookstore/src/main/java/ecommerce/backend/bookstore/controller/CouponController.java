package ecommerce.backend.bookstore.controller;

import ecommerce.backend.bookstore.dto.ApiResponse;
import ecommerce.backend.bookstore.dto.request.CouponRequest;
import ecommerce.backend.bookstore.dto.response.CouponResponse;
import ecommerce.backend.bookstore.exception.DuplicateProductException;
import ecommerce.backend.bookstore.service.ICouponService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/coupon")
public class CouponController {

    private final ICouponService couponService;

    public CouponController(ICouponService couponService) {
        this.couponService = couponService;
    }

    // Get all Coupones
    @GetMapping
    public ResponseEntity<ApiResponse<Page<CouponResponse>>> getAllCoupones(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<CouponResponse> response = couponService.getAll(page, size);
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Coupones retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving all Coupones", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve Coupones", null)
            );
        }
    }

    // Get Coupon by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CouponResponse>> getCouponById(@PathVariable Long id) {
        try {
            CouponResponse response = couponService.getEntityById(id);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "Coupon not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Coupon retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving Coupon with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve Coupon", null)
            );
        }
    }

    // Create Coupon
    @PostMapping
    public ResponseEntity<ApiResponse<CouponResponse>> createCoupon(
            @RequestBody @Valid CouponRequest request) {
        try {
            CouponResponse response = couponService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>("201", "Coupon created successfully", response)
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
            log.error("Error creating Coupon", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to create Coupon", null)
            );
        }
    }

    // Update Coupon
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CouponResponse>> updateCoupon(
            @PathVariable Long id,
            @RequestBody @Valid CouponRequest request) {
        try {
            CouponResponse response = couponService.update(id, request);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "Coupon not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Coupon updated successfully", response)
            );
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>("400", e.getMessage(), null)
            );
        } catch (Exception e) {
            log.error("Error updating Coupon with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to update Coupon", null)
            );
        }
    }

    // Delete Coupon
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCoupon(@PathVariable Long id) {
        try {
            boolean deleted = couponService.delete(id);
            if (deleted) {
                return ResponseEntity.ok(
                        new ApiResponse<>("200", "Coupon deleted successfully", null)
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>("404", "Coupon not found", null)
            );
        } catch (Exception e) {
            log.error("Error deleting Coupon with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to delete Coupon", null)
            );
        }
    }
}


