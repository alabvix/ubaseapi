package br.com.alabvix.ubaseapi.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UsernameAlreadyExistsValidatorTest {

    @Mock
    private UsernameAlreadyExists usernameAlreadyExists;

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @Mock
    private UserRepository repository;

    private UsernameAlreadyExistsValidator validator;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        validator = new UsernameAlreadyExistsValidator();
        validator.initialize(usernameAlreadyExists);
        ReflectionTestUtils.setField(validator, "repository", repository);
    }

    @Test
    @DisplayName("Given an existent username then validation must fail")
    public void isValid_existentUsername_mustFail() {

        final String existentUsername = "tester";

        User savedUser = new User("1213443455",
                "tester",
                "existentEmail",
                "12345",
                new Date());
        Optional<User> opUser = Optional.of(savedUser);
        when(repository.findByUsername(anyString())).thenReturn(opUser);

        assertFalse(validator.isValid(existentUsername, constraintValidatorContext));

    }

    @Test
    @DisplayName("Given an username that's no exist in database then validation must pass")
    public void isValid_existentPassword_mustPass() {

        final String username = "test-user";

        Optional<User> opUser = Optional.empty();
        when(repository.findByUsername(anyString())).thenReturn(opUser);

        assertTrue(validator.isValid(username,null));

    }

}
