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
                .parent(categoryRepo.findById(request.getParentId()).orElseThrow(() -> new RuntimeException("not found category")))
                .build();
        return category;
    }

    public CategoryResponse toDTO (Category category){
        CategoryResponse response = CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(category.getParent().getId())
                .build();
        return response;
    }

    public void toUpdate(Category entity, CategoryRequest request) {
        // Update the entity with request values
        entity.setName(request.getName());
        entity.setParent(categoryRepo.findById(request.getParentId()).orElseThrow(() -> new RuntimeException("not found cate parent id")));
    }
}
