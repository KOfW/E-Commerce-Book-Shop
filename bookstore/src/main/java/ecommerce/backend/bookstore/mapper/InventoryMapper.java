package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.CategoryRequest;
import ecommerce.backend.bookstore.dto.request.InventoryRequest;
import ecommerce.backend.bookstore.dto.response.InventoryResponse;
import ecommerce.backend.bookstore.entity.Inventory;
import ecommerce.backend.bookstore.repository.InventoryRepo;
import ecommerce.backend.bookstore.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryMapper {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private InventoryRepo inventoryRepo;

    public Inventory toEntity (InventoryRequest request){
        Inventory inventory = Inventory.builder()
                .quantity(request.getQuantity())
                .product(productRepo.getById(request.getProductId()))
                .build();

        return inventory;
    }

    public InventoryResponse toDTO (Inventory inventory){
        InventoryResponse inventoryResponse = InventoryResponse.builder()
                .id(inventory.getId())
                .quantity(inventory.getQuantity())
                .productId(inventory.getProduct().getId())
                .build();

        return inventoryResponse;
    }

    public void toUpdate(Inventory entity, InventoryRequest request) {
        // Update the entity with request values
        entity.setQuantity(request.getQuantity());
    }
}
