package ecommerce.backend.bookstore.service.impl;

import ecommerce.backend.bookstore.dto.request.RatingRequest;
import ecommerce.backend.bookstore.dto.response.RatingResponse;
import ecommerce.backend.bookstore.entity.Rating;
import ecommerce.backend.bookstore.mapper.RatingMapper;
import ecommerce.backend.bookstore.repository.RatingRepo;
import ecommerce.backend.bookstore.service.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements IRatingService {
    @Autowired
    private RatingRepo ratingRepo;
    @Autowired
    private RatingMapper ratingMapper;

    @Override
    public Page<RatingResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Rating> ratinges = ratingRepo.findAll(pageable);
        return ratinges.map(rating -> ratingMapper.toDTO(rating));
    }

    @Override
    public RatingResponse getEntityById(Long id) {
        Rating rating = ratingRepo.findById(id).orElseThrow(() -> new RuntimeException("not found rating"));
        return ratingMapper.toDTO(rating);
    }

    @Override
    public RatingResponse create(RatingRequest request) {
        return ratingMapper.toDTO(ratingMapper.toEntity(request));
    }

    @Override
    public boolean delete(Long id) {
        Rating rating = ratingRepo.findById(id).orElseThrow(() -> new RuntimeException("not found rating"));
        ratingRepo.delete(rating);
        return true;
    }

    @Override
    public RatingResponse update(Long id, RatingRequest request) {
        Rating ratingEntity = ratingRepo.findById(id).orElseThrow(() -> new RuntimeException("not found rating"));
        ratingMapper.toUpdate(ratingEntity, request);
        return ratingMapper.toDTO(ratingRepo.save(ratingEntity));
    }
}
