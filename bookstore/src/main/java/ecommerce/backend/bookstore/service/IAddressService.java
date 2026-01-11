package ecommerce.backend.bookstore.service;

import ecommerce.backend.bookstore.dto.request.AddressRequest;
import ecommerce.backend.bookstore.dto.response.AddressResponse;
import org.springframework.data.domain.Page;

public interface IAddressService {
    public Page<AddressResponse> getAll(int page, int size);
    public AddressResponse getEntityById(Long id);
    public AddressResponse create (AddressRequest request);
    public boolean delete (Long id);
    public AddressResponse update (Long id , AddressRequest request);
}
