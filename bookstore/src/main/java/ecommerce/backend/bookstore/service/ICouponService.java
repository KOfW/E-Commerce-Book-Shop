package ecommerce.backend.bookstore.service;

import ecommerce.backend.bookstore.dto.request.CouponRequest;
import ecommerce.backend.bookstore.dto.response.CouponResponse;
import org.springframework.data.domain.Page;

public interface ICouponService {
    public Page<CouponResponse> getAll(int page, int size);
    public CouponResponse getEntityById(Long id);
    public CouponResponse create (CouponRequest request);
    public boolean delete (Long id);
    public CouponResponse update (Long id , CouponRequest request);
}
