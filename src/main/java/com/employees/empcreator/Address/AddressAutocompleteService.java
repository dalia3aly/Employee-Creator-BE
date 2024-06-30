package com.employees.empcreator.Address;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AddressAutocompleteService {

    @Value("${google.maps.api.key}")
    private String apiKey;

    @Value("${google.maps.api.baseurl}")
    private String baseUrl;

    public String getAutocompleteSuggestions(String input) {
        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/place/autocomplete/json")
                .queryParam("input", input)
                .queryParam("components", "country:au")
                .queryParam("key", apiKey)
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }
}
