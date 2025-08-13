package ecommerce.backend.bookstore.service.impl;

import ecommerce.backend.bookstore.dto.request.CategoryRequest;
import ecommerce.backend.bookstore.dto.response.CategoryResponse;
import ecommerce.backend.bookstore.entity.Category;
import ecommerce.backend.bookstore.mapper.CategoryMapper;
import ecommerce.backend.bookstore.repository.CategoryRepo;
import ecommerce.backend.bookstore.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Page<CategoryResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryes = categoryRepo.findAll(pageable);
        return categoryes.map(category -> categoryMapper.toDTO(category));
    }

    @Override
    public CategoryResponse getEntityById(Long id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("not found category"));
        return categoryMapper.toDTO(category);
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {
        return categoryMapper.toDTO(categoryMapper.toEntity(request));
    }

    @Override
    public boolean delete(Long id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("not found category"));
        categoryRepo.delete(category);
        return true;
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest request) {
        Category categoryEntity = categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("not found category"));
        categoryMapper.toUpdate(categoryEntity, request);
        return categoryMapper.toDTO(categoryRepo.save(categoryEntity));
    }
}
