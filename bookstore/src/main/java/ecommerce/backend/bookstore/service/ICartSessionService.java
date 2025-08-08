package ecommerce.backend.bookstore.service;

import ecommerce.backend.bookstore.dto.request.CartSessionRequest;
import ecommerce.backend.bookstore.dto.response.CartSessionResponse;
import org.springframework.data.domain.Page;

public interface ICartSessionService {
    public Page<CartSessionResponse> getAll(int page, int size);
    public CartSessionResponse getEntityById(Long id);
    public CartSessionResponse create (CartSessionRequest request);
    public boolean delete (Long id);
    public CartSessionResponse update (Long id , CartSessionRequest request);
}
