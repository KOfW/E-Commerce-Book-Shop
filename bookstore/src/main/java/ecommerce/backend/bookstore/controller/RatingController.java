package ecommerce.backend.bookstore.controller;

import ecommerce.backend.bookstore.dto.ApiResponse;
import ecommerce.backend.bookstore.dto.request.RatingRequest;
import ecommerce.backend.bookstore.dto.response.RatingResponse;
import ecommerce.backend.bookstore.exception.DuplicateProductException;
import ecommerce.backend.bookstore.service.IRatingService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/rating")
public class RatingController {

    private final IRatingService ratingService;

    public RatingController(IRatingService ratingService) {
        this.ratingService = ratingService;
    }

    // Get all Ratinges
    @GetMapping
    public ResponseEntity<ApiResponse<Page<RatingResponse>>> getAllRatinges(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<RatingResponse> response = ratingService.getAll(page, size);
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Ratinges retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving all Ratinges", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve Ratinges", null)
            );
        }
    }

    // Get Rating by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RatingResponse>> getRatingById(@PathVariable Long id) {
        try {
            RatingResponse response = ratingService.getEntityById(id);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "Rating not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Rating retrieved successfully", response)
            );
        } catch (Exception e) {
            log.error("Error retrieving Rating with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to retrieve Rating", null)
            );
        }
    }

    // Create Rating
    @PostMapping
    public ResponseEntity<ApiResponse<RatingResponse>> createRating(
            @RequestBody @Valid RatingRequest request) {
        try {
            RatingResponse response = ratingService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>("201", "Rating created successfully", response)
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
            log.error("Error creating Rating", e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to create Rating", null)
            );
        }
    }

    // Update Rating
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RatingResponse>> updateRating(
            @PathVariable Long id,
            @RequestBody @Valid RatingRequest request) {
        try {
            RatingResponse response = ratingService.update(id, request);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>("404", "Rating not found", null)
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>("200", "Rating updated successfully", response)
            );
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>("400", e.getMessage(), null)
            );
        } catch (Exception e) {
            log.error("Error updating Rating with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to update Rating", null)
            );
        }
    }

    // Delete Rating
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRating(@PathVariable Long id) {
        try {
            boolean deleted = ratingService.delete(id);
            if (deleted) {
                return ResponseEntity.ok(
                        new ApiResponse<>("200", "Rating deleted successfully", null)
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>("404", "Rating not found", null)
            );
        } catch (Exception e) {
            log.error("Error deleting Rating with ID {}", id, e);
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("500", "Failed to delete Rating", null)
            );
        }
    }
}


