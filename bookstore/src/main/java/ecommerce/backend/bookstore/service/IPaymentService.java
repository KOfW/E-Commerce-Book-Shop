package ecommerce.backend.bookstore.service;

import ecommerce.backend.bookstore.dto.request.PaymentRequest;
import ecommerce.backend.bookstore.dto.response.PaymentResponse;
import org.springframework.data.domain.Page;

public interface IPaymentService {
    public Page<PaymentResponse> getAll(int page, int size);
    public PaymentResponse getEntityById(Long id);
    public PaymentResponse create (PaymentRequest request);
    public boolean delete (Long id);
    public PaymentResponse update (Long id , PaymentRequest request);
}
