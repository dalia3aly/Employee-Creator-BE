package com.employees.empcreator.User;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateUserDTO {
    @NotBlank(message = "Please enter a Username")
    @Pattern(regexp = "\\d{8}", message = "Username should be 8 digits")
    private String username;

    @NotBlank(message = "Please enter a strong password")
    private String password;

    @Pattern(regexp = "^(.+)@(.+)$", message = "Please enter a valid email")
    @NotBlank(message = "Please enter a valid email")
    private String email;

    @NotNull(message = "Please enter a date")
    private LocalDate createdAt;

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

        // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

}
