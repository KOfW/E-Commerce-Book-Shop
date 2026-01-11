package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.PaymentMethodRequest;
import ecommerce.backend.bookstore.dto.response.PaymentMethodResponse;
import ecommerce.backend.bookstore.entity.PaymentMethod;
import ecommerce.backend.bookstore.repository.PaymentMethodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodMapper {

    @Autowired
    private PaymentMethodRepo paymentMethodRepo;

    public PaymentMethod toEntity (PaymentMethodRequest request){
        PaymentMethod response = PaymentMethod.builder()
                .methodCode(request.getMethodCode())
                .methodName(request.getMethodName())
                .description(request.getDescription())
                .build();

        return response;
    }

    public PaymentMethodResponse toDTO (PaymentMethod paymentMethod){
        PaymentMethodResponse paymentMethodResponse = PaymentMethodResponse.builder()
                .id(paymentMethod.getId())
                .methodCode(paymentMethod.getMethodCode())
                .methodName(paymentMethod.getMethodName())
                .description(paymentMethod.getDescription())
                .build();

        return paymentMethodResponse;
    }

    public void toUpdate(PaymentMethod entity, PaymentMethodRequest request) {
        // Update the entity with request values
        entity.setMethodCode(request.getMethodCode());
        entity.setMethodName(request.getMethodName());
        entity.setDescription(request.getDescription());
    }
}
