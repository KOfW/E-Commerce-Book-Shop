package ecommerce.backend.bookstore.service.impl;

import ecommerce.backend.bookstore.dto.request.OrderRequest;
import ecommerce.backend.bookstore.dto.response.OrderResponse;
import ecommerce.backend.bookstore.entity.Order;
import ecommerce.backend.bookstore.mapper.OrderMapper;
import ecommerce.backend.bookstore.repository.OrderRepo;
import ecommerce.backend.bookstore.service.IOrderSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderSerivce {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Page<OrderResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orderes = orderRepo.findAll(pageable);
        return orderes.map(order -> orderMapper.toDTO(order));
    }

    @Override
    public OrderResponse getEntityById(Long id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("not found order"));
        return orderMapper.toDTO(order);
    }

    @Override
    public OrderResponse create(OrderRequest request) {
        return orderMapper.toDTO(orderRepo.save(orderMapper.toEntity(request)));
    }

    @Override
    public boolean delete(Long id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("not found order"));
        orderRepo.delete(order);
        return true;
    }

    @Override
    public OrderResponse update(Long id, OrderRequest request) {
        Order orderEntity = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("not found order"));
        orderMapper.toUpdate(orderEntity, request);
        return orderMapper.toDTO(orderRepo.save(orderEntity));
    }
}
