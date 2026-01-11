package ecommerce.backend.bookstore.service;

import ecommerce.backend.bookstore.dto.request.ProductRequest;
import ecommerce.backend.bookstore.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface IProductService {
    public ProductResponse create(ProductRequest request);
    public boolean delete(Long id);
    public ProductResponse update(ProductRequest request, Long id);
    public Page<ProductResponse> getAll(int page, int size);

    public ProductResponse getById(Long id);
}
