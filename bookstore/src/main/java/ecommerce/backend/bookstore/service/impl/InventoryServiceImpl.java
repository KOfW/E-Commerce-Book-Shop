package ecommerce.backend.bookstore.service.impl;

import ecommerce.backend.bookstore.dto.request.InventoryRequest;
import ecommerce.backend.bookstore.dto.response.InventoryResponse;
import ecommerce.backend.bookstore.entity.Inventory;
import ecommerce.backend.bookstore.mapper.InventoryMapper;
import ecommerce.backend.bookstore.repository.InventoryRepo;
import ecommerce.backend.bookstore.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements IInventoryService {
    @Autowired
    private InventoryRepo inventoryRepo;
    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public Page<InventoryResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Inventory> inventoryes = inventoryRepo.findAll(pageable);
        return inventoryes.map(inventory -> inventoryMapper.toDTO(inventory));
    }

    @Override
    public InventoryResponse getEntityById(Long id) {
        Inventory inventory = inventoryRepo.findById(id).orElseThrow(() -> new RuntimeException("not found inventory"));
        return inventoryMapper.toDTO(inventory);
    }

    @Override
    public InventoryResponse create(InventoryRequest request) {
        return inventoryMapper.toDTO(inventoryMapper.toEntity(request));
    }

    @Override
    public boolean delete(Long id) {
        Inventory inventory = inventoryRepo.findById(id).orElseThrow(() -> new RuntimeException("not found inventory"));
        inventoryRepo.delete(inventory);
        return true;
    }

    @Override
    public InventoryResponse update(Long id, InventoryRequest request) {
        Inventory inventoryEntity = inventoryRepo.findById(id).orElseThrow(() -> new RuntimeException("not found inventory"));
        inventoryMapper.toUpdate(inventoryEntity, request);
        return inventoryMapper.toDTO(inventoryRepo.save(inventoryEntity));
    }
}
