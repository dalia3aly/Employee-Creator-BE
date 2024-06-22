package com.employees.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateUserDTO {

    @NotBlank(message = "Please enter a Username")
    @Pattern(regexp = "\\d{8}", message = "Username should be 8 digits")
    private String username;

    @NotBlank(message = "Please enter a strong password")
    private String password;

    @NotNull(message = "Role is mandatory")
    private Role role;

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}