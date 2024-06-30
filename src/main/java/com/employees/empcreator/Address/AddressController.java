package com.employees.empcreator.Address;

import com.employees.empcreator.Address.dto.CreateAddressDTO;
import com.employees.empcreator.Address.dto.UpdateAddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/{employee_id}/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody CreateAddressDTO addressDTO) {
        Address address = addressService.createAddress(addressDTO);
        return ResponseEntity.ok(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody UpdateAddressDTO addressDTO) {
        Address address = addressService.updateAddress(id, addressDTO);
        return ResponseEntity.ok(address);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Optional<Address> address = addressService.getAddressById(id);
        if (address.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(address.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
