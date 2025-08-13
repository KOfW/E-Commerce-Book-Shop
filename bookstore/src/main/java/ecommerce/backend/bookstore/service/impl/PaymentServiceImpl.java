package ecommerce.backend.bookstore.service.impl;

import ecommerce.backend.bookstore.dto.request.PaymentRequest;
import ecommerce.backend.bookstore.dto.response.PaymentResponse;
import ecommerce.backend.bookstore.entity.Payment;
import ecommerce.backend.bookstore.mapper.PaymentMapper;
import ecommerce.backend.bookstore.repository.PaymentRepo;
import ecommerce.backend.bookstore.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements IPaymentService {
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public Page<PaymentResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Payment> paymentes = paymentRepo.findAll(pageable);
        return paymentes.map(payment -> paymentMapper.toDTO(payment));
    }

    @Override
    public PaymentResponse getEntityById(Long id) {
        Payment payment = paymentRepo.findById(id).orElseThrow(() -> new RuntimeException("not found payment"));
        return paymentMapper.toDTO(payment);
    }

    @Override
    public PaymentResponse create(PaymentRequest request) {
        return paymentMapper.toDTO(paymentMapper.toEntity(request));
    }

    @Override
    public boolean delete(Long id) {
        Payment payment = paymentRepo.findById(id).orElseThrow(() -> new RuntimeException("not found payment"));
        paymentRepo.delete(payment);
        return true;
    }

    @Override
    public PaymentResponse update(Long id, PaymentRequest request) {
        Payment paymentEntity = paymentRepo.findById(id).orElseThrow(() -> new RuntimeException("not found payment"));
        paymentMapper.toUpdate(paymentEntity, request);
        return paymentMapper.toDTO(paymentRepo.save(paymentEntity));
    }
}
