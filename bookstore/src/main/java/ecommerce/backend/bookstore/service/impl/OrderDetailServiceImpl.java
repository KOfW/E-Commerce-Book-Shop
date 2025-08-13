package ecommerce.backend.bookstore.service.impl;

import ecommerce.backend.bookstore.dto.request.OrderDetailRequest;
import ecommerce.backend.bookstore.dto.response.OrderDetailResponse;
import ecommerce.backend.bookstore.entity.OrderDetail;
import ecommerce.backend.bookstore.mapper.OrderDetailMapper;
import ecommerce.backend.bookstore.repository.OrderDetailRepo;
import ecommerce.backend.bookstore.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {
    @Autowired
    private OrderDetailRepo orderDetailRepo;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public Page<OrderDetailResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderDetail> orderDetailes = orderDetailRepo.findAll(pageable);
        return orderDetailes.map(orderDetail -> orderDetailMapper.toDTO(orderDetail));
    }

    @Override
    public OrderDetailResponse getEntityById(Long id) {
        OrderDetail orderDetail = orderDetailRepo.findById(id).orElseThrow(() -> new RuntimeException("not found orderDetail"));
        return orderDetailMapper.toDTO(orderDetail);
    }

    @Override
    public OrderDetailResponse create(OrderDetailRequest request) {
        return orderDetailMapper.toDTO(orderDetailRepo.save(orderDetailMapper.toEntity(request)));
    }

    @Override
    public boolean delete(Long id) {
        OrderDetail orderDetail = orderDetailRepo.findById(id).orElseThrow(() -> new RuntimeException("not found orderDetail"));
        orderDetailRepo.delete(orderDetail);
        return true;
    }

    @Override
    public OrderDetailResponse update(Long id, OrderDetailRequest request) {
        OrderDetail orderDetailEntity = orderDetailRepo.findById(id).orElseThrow(() -> new RuntimeException("not found orderDetail"));
        orderDetailMapper.toUpdate(orderDetailEntity, request);
        return orderDetailMapper.toDTO(orderDetailRepo.save(orderDetailEntity));
    }
}