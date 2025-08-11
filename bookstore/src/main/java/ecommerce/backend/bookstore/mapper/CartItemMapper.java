package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.CartItemRequest;
import ecommerce.backend.bookstore.dto.response.CartItemResponse;
import ecommerce.backend.bookstore.entity.CartItem;
import ecommerce.backend.bookstore.repository.CartItemRepo;
import ecommerce.backend.bookstore.repository.CartSessionRepo;
import ecommerce.backend.bookstore.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemMapper {

    @Autowired
    private CartItemRepo cartItemRepo;
    @Autowired
    private CartSessionRepo cartSessionRepo;
    @Autowired
    private ProductRepo productRepo;

    public CartItem toEntity (CartItemRequest request){
        CartItem cartItem = CartItem.builder()
                .amount(request.getAmount())
                .total(request.getTotal())
                .cartSession(cartSessionRepo.findById(request.getCartSessionId()).orElseThrow(() -> new RuntimeException("not found cart item")))
                .product(productRepo.findById(request.getProductId()).orElseThrow(() -> new RuntimeException("not found cart item")))
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

    public void toUpdate(CartItem entity, CartItemRequest request) {
        // Update the entity with request values
        entity.setAmount(request.getAmount());
        entity.setTotal(request.getTotal());
        entity.setProduct(productRepo.findById(request.getProductId()).orElseThrow(() -> new RuntimeException("not found cart item")));
        entity.setCartSession(cartSessionRepo.findById(request.getCartSessionId()).orElseThrow(() -> new RuntimeException("not found cart session")));
    }
}
