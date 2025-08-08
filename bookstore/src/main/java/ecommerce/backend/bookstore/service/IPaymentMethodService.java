package ecommerce.backend.bookstore.service;

import ecommerce.backend.bookstore.dto.request.PaymentMethodRequest;
import ecommerce.backend.bookstore.dto.response.PaymentMethodResponse;
import org.springframework.data.domain.Page;

public interface IPaymentMethodService {
    public Page<PaymentMethodResponse> getAll(int page, int size);
    public PaymentMethodResponse getEntityById(Long id);
    public PaymentMethodResponse create (PaymentMethodRequest request);
    public boolean delete (Long id);
    public PaymentMethodResponse update (Long id , PaymentMethodRequest request);
}
