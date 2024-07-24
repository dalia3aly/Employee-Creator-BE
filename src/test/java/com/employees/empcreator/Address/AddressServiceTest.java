package com.employees.empcreator.Address;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.employees.empcreator.Address.dto.CreateAddressDTO;
import com.employees.empcreator.Address.dto.UpdateAddressDTO;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepo;

    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createAddress_WithAustralianAddress_ReturnsAddress() {
        CreateAddressDTO addressDTO = new CreateAddressDTO();
        addressDTO.setCountry("Australia");
        addressDTO.setUnitNumber("101");
        addressDTO.setStreetAddress("123 Example St");
        addressDTO.setSuburb("Sampleville");
        addressDTO.setState("NSW");
        addressDTO.setPostcode("2000");

        Address address = new Address();

        when(addressRepo.save(any(Address.class))).thenReturn(address);

        Address createdAddress = addressService.createAddress(addressDTO);

        assertNotNull(createdAddress);
        verify(addressRepo, times(1)).save(any(Address.class));
    }

    @Test
    public void createAddress_WithNonAustralianAddress_ThrowsException() {
        CreateAddressDTO addressDTO = new CreateAddressDTO();
        addressDTO.setCountry("United States");
        addressDTO.setUnitNumber("101");
        addressDTO.setStreetAddress("123 Example St");
        addressDTO.setSuburb("Sampleville");
        addressDTO.setState("California");
        addressDTO.setPostcode("8000");

        assertThrows(IllegalArgumentException.class, () -> {
            addressService.createAddress(addressDTO);
        });
    }

    @Test
    public void updateAddress_WithExistingAddress_ReturnsUpdatedAddress() {
        UpdateAddressDTO addressDTO = new UpdateAddressDTO();
        addressDTO.setCountry("Australia");
        addressDTO.setUnitNumber("101");
        addressDTO.setStreetAddress("123 Example St");
        addressDTO.setSuburb("Sampleville");
        addressDTO.setState("NSW");
        addressDTO.setPostcode("2000");

        Long addressId = 1L;
        Address existingAddress = new Address();

        when(addressRepo.findById(addressId)).thenReturn(Optional.of(existingAddress));
        when(addressRepo.save(any(Address.class))).thenReturn(existingAddress);

        Address updatedAddress = addressService.updateAddress(addressId, addressDTO);

        assertNotNull(updatedAddress);
        verify(addressRepo, times(1)).save(any(Address.class));
        verify(addressRepo, times(1)).findById(addressId);
    }

    @Test
    public void updateAddress_WithNonExistingAddress_ThrowsException() {
        UpdateAddressDTO addressDTO = new UpdateAddressDTO();
        Long addressId = 1L;

        when(addressRepo.findById(addressId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            addressService.updateAddress(addressId, addressDTO);
        });
    }

    @Test
    public void getAddressById_WithExistingAddress_ReturnsAddress() {
        Long addressId = 1L;
        Optional<Address> optionalAddress = Optional.of(new Address());

        when(addressRepo.findById(addressId)).thenReturn(optionalAddress);

        Optional<Address> foundAddress = addressService.getAddressById(addressId);

        assertTrue(foundAddress.isPresent());
        verify(addressRepo, times(1)).findById(addressId);
        verifyNoMoreInteractions(addressRepo);
    }

    @Test
    public void getAddressById_WithNonExistingAddress_ReturnsEmpty() {
        Long addressId = 1L;

        when(addressRepo.findById(addressId)).thenReturn(Optional.empty());
        Optional<Address> foundAddress = addressService.getAddressById(addressId);
        assertFalse(foundAddress.isPresent());
    }

    @Test
    public void deleteAddress_WithExistingId_PerformsDeletion() {
        Long addressId = 1L;

        doNothing().when(addressRepo).deleteById(addressId);
        addressService.deleteAddress(addressId);
        verify(addressRepo, times(1)).deleteById(addressId);
    }
}