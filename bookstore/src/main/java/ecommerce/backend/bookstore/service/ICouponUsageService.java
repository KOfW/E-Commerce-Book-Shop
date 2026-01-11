package ecommerce.backend.bookstore.service;

import ecommerce.backend.bookstore.dto.request.CouponUsageRequest;
import ecommerce.backend.bookstore.dto.response.CouponUsageResponse;
import org.springframework.data.domain.Page;

public interface ICouponUsageService {
    public Page<CouponUsageResponse> getAll(int page, int size);
    public CouponUsageResponse getEntityById(Long id);
    public CouponUsageResponse create (CouponUsageRequest request);
    public boolean delete (Long id);
    public CouponUsageResponse update (Long id , CouponUsageRequest request);
}
