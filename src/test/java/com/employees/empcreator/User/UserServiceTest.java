package com.employees.empcreator.User;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_ValidUser_UserSaved() {
        // Arrange
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setUsername("testuser1");
        createUserDTO.setPassword("testpassword");
        createUserDTO.setEmail("test@example.com");

        User user = new User();
        user.setUsername("testuser1");
        user.setPassword("encodedPassword");
        user.setEmail("test@example.com");

        when(modelMapper.map(createUserDTO, User.class)).thenReturn(user);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepo.save(any(User.class))).thenReturn(user);

        // Act
        User result = userService.createUser(createUserDTO);

        // Assert
        assertNotNull(result);
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    public void testCreateUser_NullCreateUserDTO_ThrowsIllegalArgumentException() {
        // Arrange
        CreateUserDTO createUserDTO = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> userService.createUser(createUserDTO));
    }

    @Test
    public void testCreateUser_NullUsername_ThrowsIllegalArgumentException() {
        // Arrange
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setPassword("testpassword");
        createUserDTO.setEmail("test@example.com");

        // Act & Assert
        assertThrows(NullPointerException.class, () -> userService.createUser(createUserDTO));
    }

    @Test
    public void testCreateUser_NullPassword_ThrowsIllegalArgumentException() {
        // Arrange
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setUsername("testuser");
        createUserDTO.setEmail("test@example.com");

        // Act & Assert
        assertThrows(NullPointerException.class, () -> userService.createUser(createUserDTO));
    }

    @Test
    public void testCreateUser_NullEmail_ThrowsIllegalArgumentException() {
        // Arrange
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setUsername("testuser");
        createUserDTO.setPassword("testpassword");

        // Act & Assert
        assertThrows(NullPointerException.class, () -> userService.createUser(createUserDTO));
    }
}
