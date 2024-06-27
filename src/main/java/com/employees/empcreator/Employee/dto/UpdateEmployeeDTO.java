package com.employees.empcreator.Employee.dto;

import java.time.LocalDate;

import com.employees.empcreator.Employee.ContractType;
import com.employees.empcreator.Employee.EmploymentType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public class UpdateEmployeeDTO {

    @Size(min = 1, message = "First name is mandatory")
    private String firstName;

    private String middleName;

    @Size(min = 1, message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Mobile number is mandatory")
    private String mobileNumber;

    private String residentialAddress;

    @NotBlank(message = "Contract type is mandatory")
    private ContractType contractType;

    @NotBlank(message = "Start date is mandatory")
    private LocalDate startDate;
    private LocalDate finishDate;
    private Boolean onGoing;

    @NotBlank(message = "Employment type is mandatory")
    private EmploymentType employmentType;

    @NotBlank(message = "Hours per week is mandatory")
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

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
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

    @Override
    public String toString() {
        return "UpdateEmployeeDTO [contractType=" + contractType + ", email=" + email + ", employmentType="
                + employmentType + ", finishDate=" + finishDate + ", firstName=" + firstName + ", hoursPerWeek="
                + hoursPerWeek + ", lastName=" + lastName + ", middleName=" + middleName + ", mobileNumber="
                + mobileNumber + ", onGoing=" + onGoing + ", residentialAddress=" + residentialAddress + ", startDate="
                + startDate + "]";
    }
}
