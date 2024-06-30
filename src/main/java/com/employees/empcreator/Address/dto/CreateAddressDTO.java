package com.employees.empcreator.Address.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateAddressDTO {

    @NotBlank(message = "House/unit number is required")
    private String houseNumber;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "Suburb is required")
    private String suburb;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Postcode is required")
    private String postcode;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "Employee ID is required")
    private Long employeeId;

    // Getters and setters

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
