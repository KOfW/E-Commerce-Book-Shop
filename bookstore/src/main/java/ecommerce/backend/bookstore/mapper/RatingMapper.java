package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.RatingRequest;
import ecommerce.backend.bookstore.dto.response.RatingResponse;
import ecommerce.backend.bookstore.entity.Rating;
import ecommerce.backend.bookstore.repository.RatingRepo;
import ecommerce.backend.bookstore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingMapper {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RatingRepo ratingRepo;

    public Rating toEntity (RatingRequest request){
        Rating rating = Rating.builder()
                .ratingNumber(request.getRatingNumber())
                .user(userRepo.getById(request.getUserId()))
                .parentId(request.getParentId())
                .isVerified(request.getIsVerified())
                .build();
        return rating;
    }

    public RatingResponse toDTO (Rating rating){
        RatingResponse ratingResponse = RatingResponse.builder()
                .id(rating.getId())
                .ratingNumber(rating.getRatingNumber())
                .userId(rating.getUser().getId())
                .parentId(rating.getParentId())
                .isVerified(rating.getIsVerified())
                .build();
        return ratingResponse;
    }


    public void toUpdate(Rating entity, RatingRequest request) {
        // Update the entity with request values
        entity.setParentId(request.getParentId());
        entity.setRatingNumber(request.getRatingNumber());
        entity.setIsVerified(request.getIsVerified());
        entity.setUser(userRepo.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("not found user")));
    }
}
