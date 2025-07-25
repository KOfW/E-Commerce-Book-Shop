package ecommerce.backend.bookstore.service.impl;

import ecommerce.backend.bookstore.dto.request.AddressRequest;
import ecommerce.backend.bookstore.dto.response.AddressResponse;
import ecommerce.backend.bookstore.entity.Address;
import ecommerce.backend.bookstore.mapper.AddressMapper;
import ecommerce.backend.bookstore.repository.AddressRepo;
import ecommerce.backend.bookstore.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public AddressResponse create(AddressRequest request) {
        return addressMapper.toDTO(addressMapper.toEntity(request));
    }

    @Override
    public boolean delete(Long id) {
        Address address = addressRepo.findById(id).orElseThrow(() -> new RuntimeException("not found address"));
        addressRepo.delete(address);
        return true;
    }

    @Override
    public AddressResponse update(Long id, AddressRequest request) {
        Address address = addressRepo.findById(id).orElseThrow(() -> new RuntimeException("not found address"));
        return addressMapper.toUpdate(address, request);
    }
}
