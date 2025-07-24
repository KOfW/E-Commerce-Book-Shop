package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.PaymentMethodRequest;
import ecommerce.backend.bookstore.dto.response.PaymentMethodResponse;
import ecommerce.backend.bookstore.entity.PaymentMethod;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodMapper {

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
}
