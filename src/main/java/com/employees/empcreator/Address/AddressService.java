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

    /**
     * Creates a new Address object based on the provided CreateAddressDTO object.
     *
     * @param  addressDTO  the DTO object containing the address details
     * @return             the newly created Address object
     * @throws IllegalArgumentException  if addressDTO is null or the country is not "Australia"
     */
    public Address createAddress(CreateAddressDTO addressDTO) {
        if (addressDTO == null) {
            throw new IllegalArgumentException("AddressDTO cannot be null");
        }
        String country = addressDTO.getCountry();
        if (country == null || !"Australia".equals(country)) {
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

    /**
     * Updates an existing address in the repository.
     *
     * @param  id             the ID of the address to update
     * @param  addressDTO     the DTO containing the updated address information
     * @return                 the updated Address object
     * @throws RuntimeException if the address with the given ID is not found
     * @throws IllegalArgumentException if the country in the DTO is not "Australia"
     */
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
