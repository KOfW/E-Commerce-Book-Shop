package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.CouponUsageRequest;
import ecommerce.backend.bookstore.dto.response.CouponResponse;
import ecommerce.backend.bookstore.dto.response.CouponUsageResponse;
import ecommerce.backend.bookstore.entity.CouponUsage;
import ecommerce.backend.bookstore.repository.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponUsageMapper {

    @Autowired
    private CouponRepo couponRepo;

    public CouponUsage toEntity (CouponUsageRequest request){
        CouponUsage couponUsage = CouponUsage.builder()
                .usageAmount(request.getUsageAmount())
                .coupon(couponRepo.getById(request.getCouponId()))
                .build();

        return couponUsage;
    }

    public CouponUsageResponse toDTO (CouponUsageRequest request){
        CouponUsageResponse couponUsageResponse = CouponUsageResponse.builder()
                .usageAmount(request.getUsageAmount())
                .couponId(request.getCouponId())
                .build();

        return couponUsageResponse;
    }
}
