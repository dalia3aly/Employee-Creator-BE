package com.employees.empcreator.User;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employees.empcreator.User.User;
import com.employees.empcreator.User.UserRepository;
import com.employees.empcreator.User.CreateUserDTO;
import com.employees.empcreator.User.CreateUserDTO;
import com.employees.exceptions.UserNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

      @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    // Create a new User
    public User createUser(CreateUserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return userRepo.save(user);
    }

    // Find all Users
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    // Get User by ID
    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    // Delete an User
    public boolean deleteUserById(Long id) {
        Optional<User> maybeUser = this.findById(id);
        if (maybeUser.isEmpty()) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        this.userRepo.delete(maybeUser.get());
        return true;
    }

    // Find an User by ID
    public Optional<User> findById(Long id) {
        if (id == null) {
            throw new UserNotFoundException("User ID cannot be null");
        }
        return this.userRepo.findById(id);
    }
}