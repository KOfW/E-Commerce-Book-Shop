package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.CouponRequest;
import ecommerce.backend.bookstore.dto.response.CouponResponse;
import ecommerce.backend.bookstore.entity.CartSession;
import ecommerce.backend.bookstore.entity.Coupon;
import ecommerce.backend.bookstore.repository.CartSessionRepo;
import ecommerce.backend.bookstore.repository.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponMapper {

    @Autowired
    private CouponRepo couponRepo;
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

    public CouponResponse toUpdate(Coupon entity, CouponRequest request) {
        if (entity == null) throw new RuntimeException("Entity Coupon is null");

        // Update the entity with request values
        entity.setCode(request.getCode());
        entity.setDiscountPercent(request.getDiscountPercent());
        entity.setMax_usage(request.getMax_usage());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setCartSession(cartSessionRepo.findById(request.getCartSessionId()).orElseThrow(() -> new RuntimeException("not found cart session")));

        // Save entity
        couponRepo.save(entity);

        // Now use the updated entity to build the response
        CouponResponse couponResponseUpdate = CouponResponse.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .discountPercent(entity.getDiscountPercent())
                .max_usage(entity.getMax_usage())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .cartSessionId(entity.getCartSession().getId())
                .build();

        return couponResponseUpdate;
    }
}
