package com.employees.empcreator.Address;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.employees.empcreator.Address.dto.CreateAddressDTO;
import com.employees.empcreator.Address.dto.UpdateAddressDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AddressControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(addressController).build();
    }

@Test
public void testCreateAddress() throws Exception {
    CreateAddressDTO createAddressDTO = new CreateAddressDTO();
    createAddressDTO.setCountry("Australia");
    createAddressDTO.setUnitNumber("101");
    createAddressDTO.setStreetAddress("123 Example St");
    createAddressDTO.setSuburb("Sampleville");
    createAddressDTO.setState("NSW");
    createAddressDTO.setPostcode("2000");

    Address address = new Address();
    
    address.setCountry("Australia");
    address.setUnitNumber("101");
    address.setStreetAddress("123 Example St");
    address.setSuburb("Sampleville");
    address.setState("NSW");
    address.setPostcode("2000");

    when(addressService.createAddress(any(CreateAddressDTO.class))).thenReturn(address);

    mockMvc.perform(post("/api/1/addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createAddressDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(address.getId()))
            .andExpect(jsonPath("$.country").value("Australia"))
            .andExpect(jsonPath("$.unitNumber").value("101"))
            .andExpect(jsonPath("$.streetAddress").value("123 Example St"))
            .andExpect(jsonPath("$.suburb").value("Sampleville"))
            .andExpect(jsonPath("$.state").value("NSW"))
            .andExpect(jsonPath("$.postcode").value("2000"));
}

    @Test
    public void testUpdateAddress() throws Exception {
        UpdateAddressDTO updateAddressDTO = new UpdateAddressDTO();
        updateAddressDTO.setCountry("Australia");
        updateAddressDTO.setUnitNumber("101");
        updateAddressDTO.setStreetAddress("123 Example St");
        updateAddressDTO.setSuburb("Sampleville");
        updateAddressDTO.setState("NSW");
        updateAddressDTO.setPostcode("2000");

        Long addressId = 1L;
        Address address = new Address();
        address.setCountry("Australia");
        address.setUnitNumber("101");
        address.setStreetAddress("123 Example St");
        address.setSuburb("Sampleville");
        address.setState("NSW");
        address.setPostcode("2000");

        when(addressService.updateAddress(eq(addressId), any(UpdateAddressDTO.class))).thenReturn(address);

        mockMvc.perform(put("/api/1/addresses/" + addressId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateAddressDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(address.getId()))
                .andExpect(jsonPath("$.country").value("Australia"))
                .andExpect(jsonPath("$.unitNumber").value("101"))
                .andExpect(jsonPath("$.streetAddress").value("123 Example St"))
                .andExpect(jsonPath("$.suburb").value("Sampleville"))
                .andExpect(jsonPath("$.state").value("NSW"))
                .andExpect(jsonPath("$.postcode").value("2000"));

        verify(addressService, times(1)).updateAddress(eq(addressId), any(UpdateAddressDTO.class));
    }

    @Test
    public void testGetAddressByIdNotFound() throws Exception {
        Long addressId = 1L;
        when(addressService.getAddressById(addressId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/1/addresses/" + addressId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteAddress() throws Exception {
        Long addressId = 1L;
        doNothing().when(addressService).deleteAddress(addressId);

        mockMvc.perform(delete("/api/1/addresses/" + addressId))
                .andExpect(status().isNoContent());
    }
}