package ecommerce.backend.bookstore.service.impl;

import ecommerce.backend.bookstore.dto.request.CartItemRequest;
import ecommerce.backend.bookstore.dto.response.CartItemResponse;
import ecommerce.backend.bookstore.entity.CartItem;
import ecommerce.backend.bookstore.mapper.CartItemMapper;
import ecommerce.backend.bookstore.repository.CartItemRepo;
import ecommerce.backend.bookstore.service.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements ICartItemService {
    @Autowired
    private CartItemRepo cartItemRepo;
    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public Page<CartItemResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CartItem> cartItemes = cartItemRepo.findAll(pageable);
        return cartItemes.map(cartItem -> cartItemMapper.toDTO(cartItem));
    }

    @Override
    public CartItemResponse getEntityById(Long id) {
        CartItem cartItem = cartItemRepo.findById(id).orElseThrow(() -> new RuntimeException("not found cartItem"));
        cartItemRepo.save(cartItem);
        return cartItemMapper.toDTO(cartItem);
    }

    @Override
    public CartItemResponse create(CartItemRequest request) {
        return cartItemMapper.toDTO(cartItemMapper.toEntity(request));
    }

    @Override
    public boolean delete(Long id) {
        CartItem cartItem = cartItemRepo.findById(id).orElseThrow(() -> new RuntimeException("not found cartItem"));
        cartItemRepo.delete(cartItem);
        return true;
    }

    @Override
    public CartItemResponse update(Long id, CartItemRequest request) {
        CartItem cartItemEntity = cartItemRepo.findById(id).orElseThrow(() -> new RuntimeException("not found cartItem"));
        cartItemMapper.toUpdate(cartItemEntity, request);
        return cartItemMapper.toDTO(cartItemRepo.save(cartItemEntity));
    }
}
