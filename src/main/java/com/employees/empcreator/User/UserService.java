package com.employees.empcreator.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.employees.exceptions.UserNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Creates a new user based on the provided user data transfer object (DTO).
     *
     * @param  userDTO  the user data transfer object containing the user's information
     * @return          the newly created user
     * @throws IllegalArgumentException if the userDTO is null or the user object is null
     * @throws IllegalStateException    if an error occurs while saving the user
     */
    public User createUser(CreateUserDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("CreateUserDTO is null");
        }
        User user = modelMapper.map(userDTO, User.class);
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepo.save(user);
        } catch (Exception e) {
            throw new IllegalStateException("An error occurred while saving the user");
        }
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public boolean deleteUserById(Long id) {
        Optional<User> maybeUser = this.findById(id);
        if (maybeUser.isEmpty()) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        this.userRepo.delete(maybeUser.get());
        return true;
    }

    public Optional<User> findById(Long id) {
        if (id == null) {
            throw new UserNotFoundException("User ID cannot be null");
        }
        return this.userRepo.findById(id);
    }
}