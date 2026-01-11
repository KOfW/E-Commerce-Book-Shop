package ecommerce.backend.bookstore.service.impl;

import ecommerce.backend.bookstore.dto.request.CouponRequest;
import ecommerce.backend.bookstore.dto.response.CouponResponse;
import ecommerce.backend.bookstore.entity.Coupon;
import ecommerce.backend.bookstore.mapper.CouponMapper;
import ecommerce.backend.bookstore.repository.CouponRepo;
import ecommerce.backend.bookstore.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl implements ICouponService {
    @Autowired
    private CouponRepo couponRepo;
    @Autowired
    private CouponMapper couponMapper;

    @Override
    public Page<CouponResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Coupon> coupones = couponRepo.findAll(pageable);
        return coupones.map(coupon -> couponMapper.toDTO(coupon));
    }

    @Override
    public CouponResponse getEntityById(Long id) {
        Coupon coupon = couponRepo.findById(id).orElseThrow(() -> new RuntimeException("not found coupon"));
        return couponMapper.toDTO(coupon);
    }

    @Override
    public CouponResponse create(CouponRequest request) {
        return couponMapper.toDTO(couponRepo.save(couponMapper.toEntity(request)));
    }

    @Override
    public boolean delete(Long id) {
        Coupon coupon = couponRepo.findById(id).orElseThrow(() -> new RuntimeException("not found coupon"));
        couponRepo.delete(coupon);
        return true;
    }

    @Override
    public CouponResponse update(Long id, CouponRequest request) {
        Coupon couponEntity = couponRepo.findById(id).orElseThrow(() -> new RuntimeException("not found coupon"));
        couponMapper.toUpdate(couponEntity, request);
        return couponMapper.toDTO(couponRepo.save(couponEntity));
    }
}
