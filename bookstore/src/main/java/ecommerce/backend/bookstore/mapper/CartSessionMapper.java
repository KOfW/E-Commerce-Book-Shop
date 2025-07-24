package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.CartSessionRequest;
import ecommerce.backend.bookstore.dto.response.CartSessionResponse;
import ecommerce.backend.bookstore.entity.CartSession;
import ecommerce.backend.bookstore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartSessionMapper {

    @Autowired
    private UserRepo userRepo;

    public CartSession toEntity (CartSessionRequest request){
        CartSession cartSession = CartSession.builder()
                .total(request.getTotal())
                .user(userRepo.getById(request.getUserId()))
                .build();
        return cartSession;
    }

    public CartSessionResponse toDTO (CartSession cartSession){
        CartSessionResponse response = CartSessionResponse.builder()
                .id(cartSession.getId())
                .total(cartSession.getTotal())
                .userId(cartSession.getUser().getId())
                .build();
        return response;
    }
}
