package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.AddressRequest;
import ecommerce.backend.bookstore.dto.request.AuthorRequest;
import ecommerce.backend.bookstore.dto.response.AddressResponse;
import ecommerce.backend.bookstore.dto.response.AuthorResponse;
import ecommerce.backend.bookstore.entity.Address;
import ecommerce.backend.bookstore.entity.Author;
import ecommerce.backend.bookstore.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorMapper {

    @Autowired
    private AuthorRepo authorRepo;

    public Author toEntity (AuthorRequest request){
        Author author = Author.builder()
                .name(request.getName())
                .build();
        return author;
    }

    public AuthorResponse toDTO (Author author){
        AuthorResponse authorResponse = AuthorResponse.builder()
                .name(author.getName())
                .build();
        return authorResponse;
    }

    public AuthorResponse toUpdate(Author entity, AuthorRequest request) {
        if (entity == null) throw new RuntimeException("Entity author is null");

        // Update the entity with request values
        entity.setName(request.getName());

        // Save entity
        authorRepo.save(entity);

        // Now use the updated entity to build the response
        AuthorResponse authorResponseUpdate = AuthorResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();

        return authorResponseUpdate;
    }
}
