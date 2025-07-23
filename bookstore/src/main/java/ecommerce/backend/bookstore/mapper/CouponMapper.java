package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.CouponRequest;
import ecommerce.backend.bookstore.dto.response.CouponResponse;
import ecommerce.backend.bookstore.entity.CartSession;
import ecommerce.backend.bookstore.entity.Coupon;
import ecommerce.backend.bookstore.repository.CartSessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponMapper {

    @Autowired
    private CartSessionRepo cartSessionRepo;

    public Coupon toEntity (CouponRequest request){
        Coupon coupon = Coupon.builder()
                .code(request.getCode())
                .discountPercent(request.getDiscountPercent())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();
        return coupon;
    }

    public CouponResponse toDTO (Coupon coupon){

        if(coupon.getCartSession() != null){
            CouponResponse response = CouponResponse.builder()
                    .id(coupon.getId())
                    .code(coupon.getCode())
                    .discountPercent(coupon.getDiscountPercent())
                    .startDate(coupon.getStartDate())
                    .endDate(coupon.getEndDate())
                    .cartSessionId(coupon.getCartSession().getId())
                    .build();
            return response;
        }

        CouponResponse response = CouponResponse.builder()
                .id(coupon.getId())
                .code(coupon.getCode())
                .discountPercent(coupon.getDiscountPercent())
                .startDate(coupon.getStartDate())
                .endDate(coupon.getEndDate())
                .cartSessionId(null)
                .build();

        return response;
    }
}
