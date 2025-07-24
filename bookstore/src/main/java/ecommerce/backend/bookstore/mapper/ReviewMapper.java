package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.ReviewRequest;
import ecommerce.backend.bookstore.dto.response.ReviewResponse;
import ecommerce.backend.bookstore.entity.Review;
import ecommerce.backend.bookstore.repository.ProductRepo;
import ecommerce.backend.bookstore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewMapper {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;

    public Review toEntity (ReviewRequest request){
        Review review = Review.builder()
                .isVerified(request.getIsVerified())
                .content(request.getContent())
                .parent(request.getParent())
                .user(userRepo.getById(request.getUserId()))
                .product(productRepo.getById(request.getProductId()))
                .build();

        return review;
    }

    public ReviewResponse toDTO (Review review){
        ReviewResponse response = ReviewResponse
                .builder()
                .id(review.getId())
                .isVerified(review.getIsVerified())
                .content(review.getContent())
                .parent(review.getParent())
                .userId(review.getUser().getId())
                .productId(review.getProduct().getId())
                .build();

        return response;
    }
}
