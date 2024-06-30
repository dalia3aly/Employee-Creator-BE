package com.employees.empcreator.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address")
public class AddressAutocompleteController {

    @Autowired
    private AddressAutocompleteService addressAutocompleteService;

    @GetMapping("/autocomplete")
    public ResponseEntity<String> getAutocompleteSuggestions(@RequestParam String input) {
        String suggestions = addressAutocompleteService.getAutocompleteSuggestions(input);
        return ResponseEntity.ok(suggestions);
    }
}
