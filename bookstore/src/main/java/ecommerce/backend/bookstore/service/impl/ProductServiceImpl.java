package ecommerce.backend.bookstore.service.impl;

import ecommerce.backend.bookstore.dto.request.ProductRequest;
import ecommerce.backend.bookstore.dto.response.ProductResponse;
import ecommerce.backend.bookstore.entity.Product;
import ecommerce.backend.bookstore.mapper.ProductMapper;
import ecommerce.backend.bookstore.repository.ProductRepo;
import ecommerce.backend.bookstore.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    @Autowired
    public ProductServiceImpl(ProductRepo productRepo, ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        Product product = productMapper.toEntity(request);
        return productMapper.toDTO(product);
    }

    @Override
    public boolean delete(Long id) {
        Product product = productRepo.getProductsById(id);
        productRepo.delete(product);
        return true;
    }

    @Override
    public ProductResponse update(ProductRequest request, Long id) {
        Product productEntity = productRepo.findById(id).orElseThrow(() -> new RuntimeException("not found Product"));
        productMapper.toUpdate(request, productEntity);
        return productMapper.toDTO(productRepo.save(productEntity));
    }

    @Override
    public Page<ProductResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepo.findAll(pageable);
        return products.map(product -> productMapper.toDTO(product));
    }

    @Override
    public ProductResponse getById(Long id) {
        Product product = productRepo.getProductsById(id);
        return productMapper.toDTO(product);
    }
}
