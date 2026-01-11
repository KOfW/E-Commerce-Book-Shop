package ecommerce.backend.bookstore.service;

import ecommerce.backend.bookstore.dto.request.OrderRequest;
import ecommerce.backend.bookstore.dto.response.OrderResponse;
import org.springframework.data.domain.Page;

public interface IOrderSerivce {
    public Page<OrderResponse> getAll(int page, int size);
    public OrderResponse getEntityById(Long id);
    public OrderResponse create (OrderRequest request);
    public boolean delete (Long id);
    public OrderResponse update (Long id , OrderRequest request);
}
