package ecommerce.backend.bookstore.controller;

import ecommerce.backend.bookstore.dto.ApiResponse;
import ecommerce.backend.bookstore.dto.request.ReviewRequest;
import ecommerce.backend.bookstore.dto.response.ReviewResponse;
import ecommerce.backend.bookstore.exception.DuplicateProductException;
import ecommerce.backend.bookstore.service.IReviewService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/Review")
public class ReviewController {

    private final IReviewService reviewService;

    public ReviewController(IReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Get all Reviewes
    @GetMapping
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> getAllReviewes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<ReviewResponse> response = reviewService.getAll(page, size);
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Reviewes retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving all Reviewes", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve Reviewes", null)
            );
        }
    }

    // Get Review by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> getReviewById(@PathVariable Long id) {
        try {
            ReviewResponse response = reviewService.getEntityById(id);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "Review not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Review retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving Review with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve Review", null)
            );
        }
    }

    // Create Review
    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse>> createReview(
            @RequestBody @Valid ReviewRequest request) {
        try {
            ReviewResponse response = reviewService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>("201", "Review created successfully", response)
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
            log.error("Error creating Review", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to create Review", null)
            );
        }
    }

    // Update Review
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> updateReview(
            @PathVariable Long id,
            @RequestBody @Valid ReviewRequest request) {
        try {
            ReviewResponse response = reviewService.update(id, request);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "Review not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Review updated successfully", response)
            );
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>("400", e.getMessage(), null)
            );
        } catch (Exception e) {
            log.error("Error updating Review with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to update Review", null)
            );
        }
    }

    // Delete Review
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable Long id) {
        try {
            boolean deleted = reviewService.delete(id);
            if (deleted) {
                return ResponseEntity.ok(
                        new ApiResponse<>("200", "Review deleted successfully", null)
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>("404", "Review not found", null)
            );
        } catch (Exception e) {
            log.error("Error deleting Review with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to delete Review", null)
            );
        }
    }
}


