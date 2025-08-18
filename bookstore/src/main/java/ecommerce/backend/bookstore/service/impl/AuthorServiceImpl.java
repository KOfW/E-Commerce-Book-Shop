package ecommerce.backend.bookstore.service.impl;

import ecommerce.backend.bookstore.dto.request.AuthorRequest;
import ecommerce.backend.bookstore.dto.response.AuthorResponse;
import ecommerce.backend.bookstore.entity.Author;
import ecommerce.backend.bookstore.mapper.AuthorMapper;
import ecommerce.backend.bookstore.repository.AuthorRepo;
import ecommerce.backend.bookstore.service.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements IAuthorService {
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public Page<AuthorResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Author> authors = authorRepo.findAll(pageable);
        return authors.map(author -> authorMapper.toDTO(author));
    }

    @Override
    public AuthorResponse getEntityById(Long id) {
        Author author = authorRepo.findById(id).orElseThrow(() -> new RuntimeException("not found Author"));
        return authorMapper.toDTO(author);
    }

    @Override
    public AuthorResponse create(AuthorRequest request) {
        return authorMapper.toDTO(authorRepo.save(authorMapper.toEntity(request)));
    }

    @Override
    public boolean delete(Long id) {
        Author author = authorRepo.findById(id).orElseThrow(() -> new RuntimeException("not found Author"));
        authorRepo.delete(author);
        return true;
    }

    @Override
    public AuthorResponse update(Long id, AuthorRequest request) {
        Author authorEntity = authorRepo.findById(id).orElseThrow(() -> new RuntimeException("not found Author"));
        authorMapper.toUpdate(authorEntity, request);
        return authorMapper.toDTO(authorRepo.save(authorEntity));
    }
}
