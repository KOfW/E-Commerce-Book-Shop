package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.PaymentRequest;
import ecommerce.backend.bookstore.dto.response.PaymentResponse;
import ecommerce.backend.bookstore.entity.Payment;
import ecommerce.backend.bookstore.repository.OrderRepo;
import ecommerce.backend.bookstore.repository.PaymentMethodRepo;
import ecommerce.backend.bookstore.repository.PaymentRepo;
import ecommerce.backend.bookstore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    @Autowired
    private PaymentMethodRepo paymentMethodRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private PaymentRepo paymentRepo;

    public Payment toEntity (PaymentRequest request){
        Payment payment = Payment.builder()
                .total(request.getTotal())
                .status(request.getStatus())
                .paymentDate(request.getPaymentDate())
                .paymentMethod(paymentMethodRepo.getById(request.getPaymentMethodId()))
                .user(userRepo.getById(request.getUserId()))
                .order(orderRepo.getById(request.getOrderId()))
                .build();
        return payment;
    }
    public PaymentResponse toDTO (Payment payment){
        PaymentResponse response = PaymentResponse.builder()
                .id(payment.getId())
                .total(payment.getTotal())
                .status(payment.getStatus())
                .paymentDate(payment.getPaymentDate())
                .paymentMethodId(payment.getPaymentMethod().getId())
                .userId(payment.getUser().getId())
                .orderId(payment.getOrder().getId())
                .build();
        return response;
    }

    public void toUpdate(Payment entity, PaymentRequest request) {
        // Update the entity with request values
        entity.setTotal(request.getTotal());
        entity.setStatus(request.getStatus());
        entity.setPaymentDate(request.getPaymentDate());
        entity.setPaymentMethod(paymentMethodRepo.findById( request.getPaymentMethodId()).orElseThrow(() -> new RuntimeException("not found method payment")));
        entity.setUser(userRepo.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("not found user")));
        entity.setOrder(orderRepo.findById(request.getOrderId()).orElseThrow(() -> new RuntimeException("not found order")));
    }
}
