package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.CartItemRequest;
import ecommerce.backend.bookstore.dto.response.CartItemResponse;
import ecommerce.backend.bookstore.entity.CartItem;
import ecommerce.backend.bookstore.repository.CartSessionRepo;
import ecommerce.backend.bookstore.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemMapper {

    @Autowired
    private CartSessionRepo cartSessionRepo;
    @Autowired
    private ProductRepo productRepo;

    public CartItem toEntity (CartItemRequest request){
        CartItem cartItem = CartItem.builder()
                .amount(request.getAmount())
                .total(request.getTotal())
                .cartSession(cartSessionRepo.getById(request.getCartSessionId()))
                .product(productRepo.getById(request.getProductId()))
                .build();
        return cartItem;
    }

    public CartItemResponse toDTO (CartItem cartItem){
        CartItemResponse cartItemResponse = CartItemResponse.builder()
                .id(cartItem.getId())
                .amount(cartItem.getAmount())
                .total(cartItem.getTotal())
                .cartSessionId(cartItem.getCartSession().getId())
                .productId(cartItem.getProduct().getId())
                .build();
        return cartItemResponse;
    }
}
