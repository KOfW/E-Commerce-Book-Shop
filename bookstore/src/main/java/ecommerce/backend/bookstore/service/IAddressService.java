package ecommerce.backend.bookstore.service;

import ecommerce.backend.bookstore.dto.request.AddressRequest;
import ecommerce.backend.bookstore.dto.response.AddressResponse;

public interface IAddressService {
    public AddressResponse create (AddressRequest request);
    public boolean delete (Long id);
    public AddressResponse update (Long id , AddressRequest request);
}
