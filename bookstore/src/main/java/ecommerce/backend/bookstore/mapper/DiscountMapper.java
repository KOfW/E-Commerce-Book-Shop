package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.DiscountRequest;
import ecommerce.backend.bookstore.dto.response.DiscountResponse;
import ecommerce.backend.bookstore.entity.Discount;
import org.springframework.stereotype.Service;

@Service
public class DiscountMapper {

    public Discount toDTO(DiscountRequest request){
        Discount discount = Discount.builder()
                .discountPercent(request.getDiscountPercent())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();

        return discount;
    }

    public DiscountResponse toEntity(Discount discount){
        DiscountResponse discountResponse = DiscountResponse.builder()
                .id(discount.getId())
                .discountPercent(discount.getDiscountPercent())
                .startDate(discount.getStartDate())
                .endDate(discount.getEndDate())
                .build();

        return discountResponse;
    }
}
