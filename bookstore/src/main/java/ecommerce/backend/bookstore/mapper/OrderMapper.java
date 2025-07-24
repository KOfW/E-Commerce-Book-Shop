package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.OrderRequest;
import ecommerce.backend.bookstore.dto.response.OrderResponse;
import ecommerce.backend.bookstore.entity.Order;
import ecommerce.backend.bookstore.repository.AddressRepo;
import ecommerce.backend.bookstore.repository.UserRepo;
import ecommerce.backend.bookstore.utils.OrderStatus;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AddressRepo addressRepo;

    public Order toEntity (OrderRequest request){
        Order order = Order.builder()
                .total(request.getTotal())
                .status(OrderStatus.PENDING)
                .orderDate(request.getOrderDate())
                .user(userRepo.getById(request.getUserId()))
                .address(addressRepo.getById(request.getShippingAddressId()))
                .build();

        return order;
    }

    public OrderResponse toDTO (Order order){
        OrderResponse orderResponse = OrderResponse.builder()
                .id(order.getId())
                .total(order.getTotal())
                .status(order.getStatus().toString())
                .orderDate(order.getOrderDate())
                .userId(order.getUser().getId())
                .shippingAddressId(order.getAddress().getId())
                .build();

        return orderResponse;
    }
 }
