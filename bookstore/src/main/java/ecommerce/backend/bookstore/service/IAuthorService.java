package ecommerce.backend.bookstore.service;

import ecommerce.backend.bookstore.dto.request.AuthorRequest;
import ecommerce.backend.bookstore.dto.response.AuthorResponse;
import org.springframework.data.domain.Page;

public interface IAuthorService {
    public Page<AuthorResponse> getAll(int page, int size);
    public AuthorResponse getEntityById(Long id);
    public AuthorResponse create (AuthorRequest request);
    public boolean delete (Long id);
    public AuthorResponse update (Long id , AuthorRequest request);
}
