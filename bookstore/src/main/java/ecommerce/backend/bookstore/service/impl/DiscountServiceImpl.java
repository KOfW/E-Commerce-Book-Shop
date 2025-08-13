package ecommerce.backend.bookstore.service.impl;

import ecommerce.backend.bookstore.dto.request.DiscountRequest;
import ecommerce.backend.bookstore.dto.response.DiscountResponse;
import ecommerce.backend.bookstore.entity.Discount;
import ecommerce.backend.bookstore.mapper.DiscountMapper;
import ecommerce.backend.bookstore.repository.DiscountRepo;
import ecommerce.backend.bookstore.service.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements IDiscountService {
    @Autowired
    private DiscountRepo discountRepo;
    @Autowired
    private DiscountMapper discountMapper;

    @Override
    public Page<DiscountResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Discount> discountes = discountRepo.findAll(pageable);
        return discountes.map(discount -> discountMapper.toDTO(discount));
    }

    @Override
    public DiscountResponse getEntityById(Long id) {
        Discount discount = discountRepo.findById(id).orElseThrow(() -> new RuntimeException("not found discount"));
        return discountMapper.toDTO(discount);
    }

    @Override
    public DiscountResponse create(DiscountRequest request) {
        return discountMapper.toDTO(discountRepo.save(discountMapper.toEntity(request)));
    }

    @Override
    public boolean delete(Long id) {
        Discount discount = discountRepo.findById(id).orElseThrow(() -> new RuntimeException("not found discount"));
        discountRepo.delete(discount);
        return true;
    }

    @Override
    public DiscountResponse update(Long id, DiscountRequest request) {
        Discount discountEntity = discountRepo.findById(id).orElseThrow(() -> new RuntimeException("not found discount"));
        discountMapper.toUpdate(discountEntity, request);
        return discountMapper.toDTO(discountRepo.save(discountEntity));
    }
}
