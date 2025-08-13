package ecommerce.backend.bookstore.service.impl;

import ecommerce.backend.bookstore.dto.request.PaymentMethodRequest;
import ecommerce.backend.bookstore.dto.response.PaymentMethodResponse;
import ecommerce.backend.bookstore.entity.PaymentMethod;
import ecommerce.backend.bookstore.mapper.PaymentMethodMapper;
import ecommerce.backend.bookstore.repository.PaymentMethodRepo;
import ecommerce.backend.bookstore.service.IPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodServiceImpl implements IPaymentMethodService {
    @Autowired
    private PaymentMethodRepo paymentMethodRepo;
    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Override
    public Page<PaymentMethodResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PaymentMethod> paymentMethodes = paymentMethodRepo.findAll(pageable);
        return paymentMethodes.map(paymentMethod -> paymentMethodMapper.toDTO(paymentMethod));
    }

    @Override
    public PaymentMethodResponse getEntityById(Long id) {
        PaymentMethod paymentMethod = paymentMethodRepo.findById(id).orElseThrow(() -> new RuntimeException("not found paymentMethod"));
        return paymentMethodMapper.toDTO(paymentMethod);
    }

    @Override
    public PaymentMethodResponse create(PaymentMethodRequest request) {
        return paymentMethodMapper.toDTO(paymentMethodMapper.toEntity(request));
    }

    @Override
    public boolean delete(Long id) {
        PaymentMethod paymentMethod = paymentMethodRepo.findById(id).orElseThrow(() -> new RuntimeException("not found paymentMethod"));
        paymentMethodRepo.delete(paymentMethod);
        return true;
    }

    @Override
    public PaymentMethodResponse update(Long id, PaymentMethodRequest request) {
        PaymentMethod paymentMethodEntity = paymentMethodRepo.findById(id).orElseThrow(() -> new RuntimeException("not found paymentMethod"));
        paymentMethodMapper.toUpdate(paymentMethodEntity, request);
        return paymentMethodMapper.toDTO(paymentMethodRepo.save(paymentMethodEntity));
    }
}
