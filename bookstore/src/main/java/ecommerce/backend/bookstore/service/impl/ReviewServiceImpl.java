package ecommerce.backend.bookstore.service.impl;

import ecommerce.backend.bookstore.dto.request.ReviewRequest;
import ecommerce.backend.bookstore.dto.response.ReviewResponse;
import ecommerce.backend.bookstore.entity.Review;
import ecommerce.backend.bookstore.mapper.ReviewMapper;
import ecommerce.backend.bookstore.repository.ReviewRepo;
import ecommerce.backend.bookstore.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements IReviewService {
    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public Page<ReviewResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviewes = reviewRepo.findAll(pageable);
        return reviewes.map(review -> reviewMapper.toDTO(review));
    }

    @Override
    public ReviewResponse getEntityById(Long id) {
        Review review = reviewRepo.findById(id).orElseThrow(() -> new RuntimeException("not found review"));
        return reviewMapper.toDTO(review);
    }

    @Override
    public ReviewResponse create(ReviewRequest request) {
        return reviewMapper.toDTO(reviewMapper.toEntity(request));
    }

    @Override
    public boolean delete(Long id) {
        Review review = reviewRepo.findById(id).orElseThrow(() -> new RuntimeException("not found review"));
        reviewRepo.delete(review);
        return true;
    }

    @Override
    public ReviewResponse update(Long id, ReviewRequest request) {
        Review reviewEntity = reviewRepo.findById(id).orElseThrow(() -> new RuntimeException("not found review"));
        reviewMapper.toUpdate(reviewEntity, request);
        return reviewMapper.toDTO(reviewRepo.save(reviewEntity));
    }
}
