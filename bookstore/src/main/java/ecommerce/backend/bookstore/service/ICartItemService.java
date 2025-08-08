package ecommerce.backend.bookstore.service;

import ecommerce.backend.bookstore.dto.request.CartItemRequest;
import ecommerce.backend.bookstore.dto.response.CartItemResponse;
import org.springframework.data.domain.Page;

public interface ICartItemService {
    public Page<CartItemResponse> getAll(int page, int size);
    public CartItemResponse getEntityById(Long id);
    public CartItemResponse create (CartItemRequest request);
    public boolean delete (Long id);
    public CartItemResponse update (Long id , CartItemRequest request);
}
