package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.CartSessionRequest;
import ecommerce.backend.bookstore.dto.response.CartSessionResponse;
import ecommerce.backend.bookstore.entity.CartSession;
import ecommerce.backend.bookstore.repository.CartSessionRepo;
import ecommerce.backend.bookstore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartSessionMapper {

    @Autowired
    private CartSessionRepo cartSessionRepo;
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

    public CartSessionResponse toUpdate(CartSession entity, CartSessionRequest request) {
        if (entity == null) throw new RuntimeException("Entity CartSession is null");

        // Update the entity with request values
        entity.setTotal(request.getTotal());
        entity.setUser(userRepo.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("not found user")));

        // Save entity
        cartSessionRepo.save(entity);

        // Now use the updated entity to build the response
        CartSessionResponse cartSessionResponseUpdate = CartSessionResponse.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .build();

        return cartSessionResponseUpdate;
    }
}
