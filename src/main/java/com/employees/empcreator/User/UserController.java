package com.employees.empcreator.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.employees.common.config.SecurityConfigs.JwtTokenUtil;
import com.employees.exceptions.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public UserController(UserService userService, AuthenticationManager authenticationManager,
            JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * Registers a user with the provided CreateUserDTO.
     *
     * @param createUserDTO the CreateUserDTO object containing user details
     * @return a ResponseEntity with the registration status
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody(required = false) CreateUserDTO createUserDTO) {
        if (createUserDTO == null) {
            return ResponseEntity.badRequest().body("Request body is missing or null");
        }
        try {
            User createdUser = userService.createUser(createUserDTO);
            if (createdUser == null) {
                throw new IllegalStateException("User could not be created");
            }
            return ResponseEntity.ok("User registered successfully");
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    /**
     * Handles the login request by authenticating the user with the provided login request.
     *
     * @param  loginRequest  the login request containing the username and password
     * @return               a ResponseEntity with the login status and JWT token if successful,
     *                       or an error message if the request body is missing or null,
     *                       or if the username or password is missing,
     *                       or if the authentication fails due to invalid username or password,
     *                       or if an unexpected error occurs
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        if (loginRequest == null) {
            return ResponseEntity.badRequest().body("Request body is missing or null");
        }

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body("Username or password is missing");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenUtil.generateToken(authentication);
            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    /**
     * Retrieves a user by their ID and returns a ResponseEntity containing the user if found,
     * or a Not Found response if the user is not found.
     *
     * @param  id  the ID of the user to retrieve
     * @return     a ResponseEntity containing the user if found, or a 404 Not Found response
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
