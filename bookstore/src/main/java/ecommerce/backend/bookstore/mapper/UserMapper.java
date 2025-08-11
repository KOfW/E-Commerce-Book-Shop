package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.UserRequest;
import ecommerce.backend.bookstore.dto.response.UserResponse;
import ecommerce.backend.bookstore.entity.User;

public class UserMapper {

    public User toEntity (UserRequest request){
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(request.getPasswordHash())
                .phone(request.getPhone())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .urlImage(request.getUrlImage())
                .active(request.getActive())
                .build();

        return user;
    }

    public UserResponse toDTO (User user){
        UserResponse response = UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .passwordHash(user.getPasswordHash())
                .phone(user.getPhone())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .urlImage(user.getUrlImage())
                .active(user.getActive())
                .build();

        return response;
    }

    public void toUpdate(User entity, UserRequest request) {
        // Update the entity with request values
        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setActive(request.getActive());
    }
}
