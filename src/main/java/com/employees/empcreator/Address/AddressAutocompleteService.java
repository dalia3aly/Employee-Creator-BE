package com.employees.empcreator.Address;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AddressAutocompleteService {

    private static final Logger logger = LoggerFactory.getLogger(AddressAutocompleteService.class);

    @Value("${google.maps.api.key}")
    private String apiKey;

    @Value("${google.maps.api.baseurl}")
    private String baseUrl;

    /**
     * Retrieves autocomplete suggestions for a given input.
     *
     * @param  input the input string for which suggestions are requested
     * @return        the autocomplete suggestions as a JSON string, or null if an error occurs
     */
    public String getAutocompleteSuggestions(String input) {
        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/place/autocomplete/json")
                .queryParam("input", input)
                .queryParam("types", "address")
                .queryParam("components", "country:au")
                .queryParam("key", apiKey)
                .toUriString();

        logger.info("Fetching autocomplete suggestions with URL: {}", url);

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            logger.info("Received autocomplete suggestions: {}", response.getBody());
            return response.getBody();
        } catch (RestClientException e) {
            logger.error("Error fetching autocomplete suggestions: ", e);
            return null;
        }
    }

    /**
     * Retrieves the details of a place using the provided place ID.
     *
     * @param placeId the ID of the place to retrieve details for
     * @return The response from the server is logged, and if successful, the
     *         response body is returned as a JSON string,
     *         or null if an error occurs
     */
    public String getPlaceDetails(String placeId) {
        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/place/details/json")
                .queryParam("place_id", placeId)
                .queryParam("key", apiKey)
                .toUriString();

        logger.info("Fetching place details with URL: {}", url);

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            logger.info("Received place details: {}", response.getBody());
            return response.getBody();
        } catch (RestClientException e) {
            logger.error("Error fetching place details: ", e);
            return null;
        }
    }
}
