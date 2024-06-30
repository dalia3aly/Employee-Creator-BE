package com.employees.empcreator.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employees.empcreator.Address.dto.CreateAddressDTO;
import com.employees.empcreator.Address.dto.UpdateAddressDTO;

import jakarta.transaction.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AddressService {

    @Autowired
    private AddressRepository addressRepo;

    public Address createAddress(CreateAddressDTO addressDTO) {
        Address address = new Address();
        address.setHouseNumber(addressDTO.getHouseNumber());
        address.setStreet(addressDTO.getStreet());
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
        Address address = existingAddress.get();
        address.setHouseNumber(addressDTO.getHouseNumber());
        address.setStreet(addressDTO.getStreet());
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
