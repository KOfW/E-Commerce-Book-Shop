package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.OrderDetailRequest;
import ecommerce.backend.bookstore.dto.response.OrderDetailResponse;
import ecommerce.backend.bookstore.entity.OrderDetail;
import ecommerce.backend.bookstore.repository.OrderDetailRepo;
import ecommerce.backend.bookstore.repository.OrderRepo;
import ecommerce.backend.bookstore.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailMapper {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private OrderDetailRepo orderDetailRepo;

    public OrderDetail toEntity (OrderDetailRequest request){
        OrderDetail orderDetail = OrderDetail.builder()
                .amount(request.getAmount())
                .total(request.getTotal())
                .order(orderRepo.getById(request.getOrderId()))
                .product(productRepo.getById(request.getProductId()))
                .build();

        return orderDetail;
    }

    public OrderDetailResponse toDTO (OrderDetail orderDetail){
        OrderDetailResponse orderDetailResponse = OrderDetailResponse.builder()
                .id(orderDetail.getId())
                .amount(orderDetail.getAmount())
                .total(orderDetail.getTotal())
                .orderId(orderDetail.getOrder().getId())
                .productId(orderDetail.getProduct().getId())
                .build();

        return orderDetailResponse;
    }

    public void toUpdate(OrderDetail entity, OrderDetailRequest request) {
        // Update the entity with request values
        entity.setAmount(request.getAmount());
        entity.setTotal(request.getTotal());
        entity.setOrder(orderRepo.findById(request.getOrderId()).orElseThrow(() -> new RuntimeException("not found order")));
        entity.setProduct(productRepo.findById(request.getProductId()).orElseThrow(() -> new RuntimeException("not found product")));
    }
}
