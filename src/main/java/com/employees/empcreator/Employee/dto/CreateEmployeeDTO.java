package com.employees.empcreator.Employee.dto;

import java.time.LocalDate;

import com.employees.empcreator.Employee.ContractType;
import com.employees.empcreator.Employee.EmploymentType;
import com.employees.empcreator.Address.dto.CreateAddressDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateEmployeeDTO {

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Mobile number should be 10 digits")
    private String mobileNumber;

    @NotNull(message = "Address is required")
    private CreateAddressDTO address;

    @NotNull(message = "Contract type is mandatory")
    private ContractType contractType;

    @NotNull(message = "Start date is mandatory")
    private LocalDate startDate;

    private LocalDate finishDate;

    private Boolean onGoing;

    @NotNull(message = "Employment type is mandatory")
    private EmploymentType employmentType;

    private Integer hoursPerWeek;

    // Getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public CreateAddressDTO getAddress() {
        return address;
    }

    public void setAddress(CreateAddressDTO address) {
        this.address = address;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public Boolean getOnGoing() {
        return onGoing;
    }

    public void setOnGoing(Boolean onGoing) {
        this.onGoing = onGoing;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public Integer getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(Integer hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }
}
