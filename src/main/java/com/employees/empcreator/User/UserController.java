// package com.employees.empcreator.User;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.bind.annotation.*;

// import io.jsonwebtoken.impl.JwtTokenizer;
// import jakarta.validation.Valid;

// @RestController
// @RequestMapping("/api/users")
// public class UserController {

//     private final UserService userService;
//     private final AuthenticationManager authenticationManager;
//     private final JwtTokenizer jwtTokenUtil;

//     public UserController(UserService userService, AuthenticationManager authenticationManager,
//             JwtTokenizer jwtTokenUtil) {
//         this.userService = userService;
//         this.authenticationManager = authenticationManager;
//         this.jwtTokenUtil = jwtTokenUtil;
//     }

//     @PostMapping("/register")
//     public ResponseEntity<?> registerUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
//         try {
//             userService.createUser(createUserDTO);
//             return ResponseEntity.ok("User registered successfully");
//         } catch (RuntimeException e) {
//             return ResponseEntity.badRequest().body(e.getMessage());
//         }
//     }

//     @PostMapping("/login")
//     public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
//         try {
//             Authentication authentication = authenticationManager.authenticate(
//                     new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

//             SecurityContextHolder.getContext().setAuthentication(authentication);
//             String jwt = jwtTokenUtil.generateToken(authentication);

//             return ResponseEntity.ok(new JwtResponse(jwt));
//         } catch (Exception e) {
//             return ResponseEntity.badRequest().body("Invalid username or password");
//         }
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<?> getUserById(@PathVariable Long id) {
//         try {
//             User user = userService.getUserById(id);
//             return ResponseEntity.ok(user);
//         } catch (RuntimeException e) {
//             return ResponseEntity.notFound().build();
//         }
//     }
// }