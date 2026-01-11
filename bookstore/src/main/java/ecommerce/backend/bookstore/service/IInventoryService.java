package ecommerce.backend.bookstore.service;

import ecommerce.backend.bookstore.dto.request.InventoryRequest;
import ecommerce.backend.bookstore.dto.response.InventoryResponse;
import org.springframework.data.domain.Page;

public interface IInventoryService {
    public Page<InventoryResponse> getAll(int page, int size);
    public InventoryResponse getEntityById(Long id);
    public InventoryResponse create (InventoryRequest request);
    public boolean delete (Long id);
    public InventoryResponse update (Long id , InventoryRequest request);
}
