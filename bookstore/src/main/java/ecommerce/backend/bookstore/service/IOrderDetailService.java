package ecommerce.backend.bookstore.service;

import ecommerce.backend.bookstore.dto.request.OrderDetailRequest;
import ecommerce.backend.bookstore.dto.response.OrderDetailResponse;
import org.springframework.data.domain.Page;

public interface IOrderDetailService {
    public Page<OrderDetailResponse> getAll(int page, int size);
    public OrderDetailResponse getEntityById(Long id);
    public OrderDetailResponse create (OrderDetailRequest request);
    public boolean delete (Long id);
    public OrderDetailResponse update (Long id , OrderDetailRequest request);
}
