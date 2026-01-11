package ecommerce.backend.bookstore.service;

import ecommerce.backend.bookstore.dto.request.DiscountRequest;
import ecommerce.backend.bookstore.dto.response.DiscountResponse;
import org.springframework.data.domain.Page;

public interface IDiscountService {
    public Page<DiscountResponse> getAll(int page, int size);
    public DiscountResponse getEntityById(Long id);
    public DiscountResponse create (DiscountRequest request);
    public boolean delete (Long id);
    public DiscountResponse update (Long id , DiscountRequest request);
}
