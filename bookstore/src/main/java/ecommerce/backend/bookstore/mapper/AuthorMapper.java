package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.AuthorRequest;
import ecommerce.backend.bookstore.dto.response.AuthorResponse;
import ecommerce.backend.bookstore.entity.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorMapper {

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
}
