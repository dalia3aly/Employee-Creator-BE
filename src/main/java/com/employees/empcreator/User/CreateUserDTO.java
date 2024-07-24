package com.employees.empcreator.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateUserDTO {
    @NotBlank(message = "Please enter a Username")
    @Size(min = 8, max = 12, message = "Username should be between 8 and 12 characters")
    private String username;

    @NotBlank(message = "Please enter a strong password")
    @Size(min = 8, max = 16, message = "Password should be between 8 and 16 characters and digits")
    private String password;

    @Pattern(regexp = "^(.+)@(.+)$", message = "Please enter a valid email")
    @NotBlank(message = "Please enter a valid email")
    private String email;

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
}
