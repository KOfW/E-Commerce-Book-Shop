package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.CategoryRequest;
import ecommerce.backend.bookstore.dto.response.CategoryResponse;
import ecommerce.backend.bookstore.entity.Category;
import ecommerce.backend.bookstore.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category toEntity (CategoryRequest request){
        Category category = Category.builder()
                .name(request.getName())
                .parentId(request.getParentId())
                .build();
        return category;
    }

    public CategoryResponse toDTO (Category category){
        CategoryResponse response = CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(category.getParentId())
                .build();
        return response;
    }

    public CategoryResponse toUpdate(Category entity, CategoryRequest request) {
        if (entity == null) throw new RuntimeException("Entity Category is null");

        // Update the entity with request values
        entity.setName(request.getName());
        entity.setParent(categoryRepo.findById(request.getParentId()).orElseThrow(() -> new RuntimeException("not found cate parent id")));

        // Save entity
        categoryRepo.save(entity);

        // Now use the updated entity to build the response
        CategoryResponse categoryResponseUpdate = CategoryResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .parentId(entity.getParent().getId())
                .build();

        return categoryResponseUpdate;
    }
}
