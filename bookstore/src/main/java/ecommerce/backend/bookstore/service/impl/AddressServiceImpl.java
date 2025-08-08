package ecommerce.backend.bookstore.service.impl;

import ecommerce.backend.bookstore.dto.request.AddressRequest;
import ecommerce.backend.bookstore.dto.response.AddressResponse;
import ecommerce.backend.bookstore.entity.Address;
import ecommerce.backend.bookstore.mapper.AddressMapper;
import ecommerce.backend.bookstore.repository.AddressRepo;
import ecommerce.backend.bookstore.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Page<AddressResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Address> addresses = addressRepo.findAll(pageable);
        return addresses.map(address -> addressMapper.toDTO(address));
    }

    @Override
    public AddressResponse getEntityById(Long id) {
        Address address = addressRepo.findById(id).orElseThrow(() -> new RuntimeException("not found address"));
        return addressMapper.toDTO(address);
    }

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
