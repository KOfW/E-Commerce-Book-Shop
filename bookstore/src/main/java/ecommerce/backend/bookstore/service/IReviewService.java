package ecommerce.backend.bookstore.service;

import ecommerce.backend.bookstore.dto.request.ReviewRequest;
import ecommerce.backend.bookstore.dto.response.ReviewResponse;
import org.springframework.data.domain.Page;

public interface IReviewService {
    public Page<ReviewResponse> getAll(int page, int size);
    public ReviewResponse getEntityById(Long id);
    public ReviewResponse create (ReviewRequest request);
    public boolean delete (Long id);
    public ReviewResponse update (Long id , ReviewRequest request);
}
