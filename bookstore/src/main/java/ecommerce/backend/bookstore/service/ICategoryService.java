package ecommerce.backend.bookstore.service;

import ecommerce.backend.bookstore.dto.request.CategoryRequest;
import ecommerce.backend.bookstore.dto.response.CategoryResponse;
import org.springframework.data.domain.Page;

public interface ICategoryService {
    public Page<CategoryResponse> getAll(int page, int size);
    public CategoryResponse getEntityById(Long id);
    public CategoryResponse create (CategoryRequest request);
    public boolean delete (Long id);
    public CategoryResponse update (Long id , CategoryRequest request);
}
