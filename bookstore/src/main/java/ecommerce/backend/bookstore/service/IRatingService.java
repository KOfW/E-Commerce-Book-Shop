package ecommerce.backend.bookstore.service;

import ecommerce.backend.bookstore.dto.request.RatingRequest;
import ecommerce.backend.bookstore.dto.response.RatingResponse;
import org.springframework.data.domain.Page;

public interface IRatingService {
    public Page<RatingResponse> getAll(int page, int size);
    public RatingResponse getEntityById(Long id);
    public RatingResponse create (RatingRequest request);
    public boolean delete (Long id);
    public RatingResponse update (Long id , RatingRequest request);
}
