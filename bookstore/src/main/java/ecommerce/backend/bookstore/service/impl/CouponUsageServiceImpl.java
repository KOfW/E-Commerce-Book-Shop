package ecommerce.backend.bookstore.service.impl;

import ecommerce.backend.bookstore.dto.request.CouponUsageRequest;
import ecommerce.backend.bookstore.dto.response.CouponUsageResponse;
import ecommerce.backend.bookstore.entity.CouponUsage;
import ecommerce.backend.bookstore.mapper.CouponUsageMapper;
import ecommerce.backend.bookstore.repository.CouponUsageRepo;
import ecommerce.backend.bookstore.service.ICouponUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CouponUsageServiceImpl implements ICouponUsageService {
    @Autowired
    private CouponUsageRepo couponUsageRepo;
    @Autowired
    private CouponUsageMapper couponUsageMapper;

    @Override
    public Page<CouponUsageResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CouponUsage> couponUsagees = couponUsageRepo.findAll(pageable);
        return couponUsagees.map(couponUsages -> couponUsageMapper.toDTO(couponUsages));
    }

    @Override
    public CouponUsageResponse getEntityById(Long id) {
        CouponUsage couponUsage = couponUsageRepo.findById(id).orElseThrow(() -> new RuntimeException("not found couponUsage"));
        return couponUsageMapper.toDTO(couponUsage);
    }

    @Override
    public CouponUsageResponse create(CouponUsageRequest request) {
        return couponUsageMapper.toDTO(couponUsageRepo.save(couponUsageMapper.toEntity(request)));
    }

    @Override
    public boolean delete(Long id) {
        CouponUsage couponUsage = couponUsageRepo.findById(id).orElseThrow(() -> new RuntimeException("not found couponUsage"));
        couponUsageRepo.delete(couponUsage);
        return true;
    }

    @Override
    public CouponUsageResponse update(Long id, CouponUsageRequest request) {
        CouponUsage couponUsageEntity = couponUsageRepo.findById(id).orElseThrow(() -> new RuntimeException("not found couponUsage"));
        couponUsageMapper.toUpdate(couponUsageEntity, request);
        return couponUsageMapper.toDTO(couponUsageRepo.save(couponUsageEntity));
    }
}