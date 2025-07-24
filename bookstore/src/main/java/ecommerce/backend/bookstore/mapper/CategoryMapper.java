package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.CategoryRequest;
import ecommerce.backend.bookstore.dto.response.CategoryResponse;
import ecommerce.backend.bookstore.entity.Category;

public class CategoryMapper {

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
}
