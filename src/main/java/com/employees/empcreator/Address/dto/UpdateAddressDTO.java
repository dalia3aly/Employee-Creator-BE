package com.employees.empcreator.Address.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateAddressDTO {

    
    private String unitNumber;

    @NotBlank(message = "Street address is required")
    private String streetAddress;

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

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
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
