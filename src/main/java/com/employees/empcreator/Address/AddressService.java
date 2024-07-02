package com.employees.empcreator.Address;

import com.employees.empcreator.Address.dto.CreateAddressDTO;
import com.employees.empcreator.Address.dto.UpdateAddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AddressService {

    @Autowired
    private AddressRepository addressRepo;

    public Address createAddress(CreateAddressDTO addressDTO) {
        if (!"Australia".equals(addressDTO.getCountry())) {
            throw new IllegalArgumentException("Please provide an Australian address");
        }
        Address address = new Address();
        address.setUnitNumber(addressDTO.getUnitNumber());
        address.setStreetAddress(addressDTO.getStreetAddress());
        address.setSuburb(addressDTO.getSuburb());
        address.setState(addressDTO.getState());
        address.setPostcode(addressDTO.getPostcode());
        address.setCountry(addressDTO.getCountry());
        return addressRepo.save(address);
    }

    public Address updateAddress(Long id, UpdateAddressDTO addressDTO) {
        Optional<Address> existingAddress = addressRepo.findById(id);
        if (existingAddress.isEmpty()) {
            throw new RuntimeException("Address not found");
        }

        if (!"Australia".equals(addressDTO.getCountry())) {
            throw new IllegalArgumentException("Please provide an Australian address");
        }

        Address address = existingAddress.get();
        address.setUnitNumber(addressDTO.getUnitNumber());
        address.setStreetAddress(addressDTO.getStreetAddress());
        address.setSuburb(addressDTO.getSuburb());
        address.setState(addressDTO.getState());
        address.setPostcode(addressDTO.getPostcode());
        address.setCountry(addressDTO.getCountry());
        return addressRepo.save(address);
    }

    public Optional<Address> getAddressById(Long id) {
        return addressRepo.findById(id);
    }

    public void deleteAddress(Long id) {
        addressRepo.deleteById(id);
    }
}
