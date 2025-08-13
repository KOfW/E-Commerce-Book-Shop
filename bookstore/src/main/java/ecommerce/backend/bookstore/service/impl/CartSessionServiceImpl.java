package ecommerce.backend.bookstore.service.impl;

import ecommerce.backend.bookstore.dto.request.CartSessionRequest;
import ecommerce.backend.bookstore.dto.response.CartSessionResponse;
import ecommerce.backend.bookstore.entity.CartSession;
import ecommerce.backend.bookstore.mapper.CartSessionMapper;
import ecommerce.backend.bookstore.repository.CartSessionRepo;
import ecommerce.backend.bookstore.service.ICartSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CartSessionServiceImpl implements ICartSessionService {
    @Autowired
    private CartSessionRepo cartSessionRepo;
    @Autowired
    private CartSessionMapper cartSessionMapper;

    @Override
    public Page<CartSessionResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CartSession> cartSessiones = cartSessionRepo.findAll(pageable);
        return cartSessiones.map(cartSession -> cartSessionMapper.toDTO(cartSession));
    }

    @Override
    public CartSessionResponse getEntityById(Long id) {
        CartSession cartSession = cartSessionRepo.findById(id).orElseThrow(() -> new RuntimeException("not found cartSession"));
        return cartSessionMapper.toDTO(cartSession);
    }

    @Override
    public CartSessionResponse create(CartSessionRequest request) {
        return cartSessionMapper.toDTO(cartSessionMapper.toEntity(request));
    }

    @Override
    public boolean delete(Long id) {
        CartSession cartSession = cartSessionRepo.findById(id).orElseThrow(() -> new RuntimeException("not found cartSession"));
        cartSessionRepo.delete(cartSession);
        return true;
    }

    @Override
    public CartSessionResponse update(Long id, CartSessionRequest request) {
        CartSession cartSessionEntity = cartSessionRepo.findById(id).orElseThrow(() -> new RuntimeException("not found cartSession"));
        cartSessionMapper.toUpdate(cartSessionEntity, request);
        return cartSessionMapper.toDTO(cartSessionRepo.save(cartSessionEntity));
    }
}
