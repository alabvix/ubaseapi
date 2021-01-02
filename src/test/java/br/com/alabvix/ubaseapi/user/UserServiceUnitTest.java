package br.com.alabvix.ubaseapi.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserServiceUnitTest {

    UserService userService;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userService = new UserService(userRepository, bCryptPasswordEncoder);
    }

    @Test
    @DisplayName("Given valid user data for sign up then create a new user")
    public void signUp_validUser_createNewUser() {

        User savedUser = new User("1213443455",
                "Josh",
                "josh@test.com", bCryptPasswordEncoder.encode("12345"),
                new Date());
        when(userRepository.save(Mockito.any(User.class))).thenReturn(savedUser);

        // given
        SignUpUser signUpUser = new SignUpUser(
                "Josh",
                "josh@test.com",
                "josh@test.com",
                "12345",
                "12345");

        // when
        User user = userService.signUp(signUpUser);

        // then
        assertNotNull(user.id);
        assertEquals(signUpUser.email, user.email);
        assertEquals(signUpUser.username, user.username);
        assertNotNull(user.password);
        assertEquals(savedUser.password, user.password);
    }

}
