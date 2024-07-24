package com.employees.empcreator.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/address")
public class AddressAutocompleteController {

    private static final Logger logger = LoggerFactory.getLogger(AddressAutocompleteController.class);

    @Autowired
    private AddressAutocompleteService addressAutocompleteService;

    /**
     * Retrieves autocomplete suggestions for a given input.
     *
     * @param  input the input string for which suggestions are requested
     * @return        the autocomplete suggestions as a JSON string, or a 500 error response if an error occurs
     */
    @GetMapping("/autocomplete")
    public ResponseEntity<String> getAutocompleteSuggestions(@RequestParam String input) {
        logger.info("Received autocomplete request for input: {}", input);
        String suggestions = addressAutocompleteService.getAutocompleteSuggestions(input);
        if (suggestions != null) {
            return ResponseEntity.ok(suggestions);
        } else {
            return ResponseEntity.status(500).body("Error fetching autocomplete suggestions");
        }
    }

    /**
     * Retrieves the details of a place using the provided place ID.
     *
     * @param  placeId the ID of the place to retrieve details for
     * @return         a ResponseEntity containing the place details as a JSON string,
     *                 or a ResponseEntity with a 500 status and an error message if an error occurs
     */
    @GetMapping("/details")
    public ResponseEntity<String> getPlaceDetails(@RequestParam String placeId) {
        logger.info("Received place details request for placeId: {}", placeId);
        String details = addressAutocompleteService.getPlaceDetails(placeId);
        if (details != null) {
            return ResponseEntity.ok(details);
        } else {
            return ResponseEntity.status(500).body("Error fetching place details");
        }
    }
}
