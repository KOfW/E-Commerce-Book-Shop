package ecommerce.backend.bookstore.mapper;

import ecommerce.backend.bookstore.dto.request.AddressRequest;
import ecommerce.backend.bookstore.dto.response.AddressResponse;
import ecommerce.backend.bookstore.entity.Address;
import ecommerce.backend.bookstore.repository.AddressRepo;
import ecommerce.backend.bookstore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressMapper {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AddressRepo addressRepo;

    public Address toEntity(AddressRequest request){
        if(request == null) throw new RuntimeException("Request Address is null");
        Address address = Address.builder()
                .street(request.getStreet())
                .city(request.getCity())
                .province(request.getProvince())
                .country(request.getCountry())
                .user(userRepo.getReferenceById(request.getUserId()))
                .build();
        return address;
    }

    public AddressResponse toDTO(Address entity){
        if(entity == null) throw new RuntimeException("Entity Address is null");
        AddressResponse addressResponse = AddressResponse.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .street(entity.getStreet())
                .city(entity.getCity())
                .province(entity.getProvince())
                .country(entity.getCountry())
                .build();
        return addressResponse;
    }

    public AddressResponse toUpdate(Address entity, AddressRequest request) {
        if (entity == null) throw new RuntimeException("Entity Address is null");

        // Update the entity with request values
        entity.setStreet(request.getStreet());
        entity.setCity(request.getCity());
        entity.setProvince(request.getProvince());
        entity.setCountry(request.getCountry());

        // Save entity
        addressRepo.save(entity);

        // Now use the updated entity to build the response
        AddressResponse addressResponseUpdate = AddressResponse.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .street(entity.getStreet())
                .city(entity.getCity())
                .province(entity.getProvince())
                .country(entity.getCountry())
                .build();

        return addressResponseUpdate;
    }
}
