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

    public User createUser(CreateUserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
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