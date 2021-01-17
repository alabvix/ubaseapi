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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmailAlreadyExistsValidatorTest {

    @Mock
    private EmailAlreadyExists emailAlreadyExists;

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @Mock
    private UserRepository repository;

    private EmailAlreadyExistsValidator validator;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        validator = new EmailAlreadyExistsValidator();
        validator.initialize(emailAlreadyExists);
        ReflectionTestUtils.setField(validator, "repository", repository);
    }

    @Test
    @DisplayName("Given an user with existent email then validation must fail")
    public void isValid_existentEmail_mustFail() {

        final String existentEmail = "testuser@test.com";

        User savedUser = new User("1213443455",
                "Test User",
                existentEmail,
                "12345",
                new Date());
        Optional<User> opUser = Optional.of(savedUser);
        when(repository.findByEmail(anyString())).thenReturn(opUser);

        assertFalse(validator.isValid(existentEmail,constraintValidatorContext));
        verify(repository).findByEmail(anyString());

    }

    @Test
    @DisplayName("Given an user with no existent email then validation must pass")
    public void isValid_existentEmail_mustPass() {

        final String existentEmail = "testuser@test.com";

        Optional<User> opUser = Optional.empty();
        when(repository.findByEmail(anyString())).thenReturn(opUser);

        assertTrue(validator.isValid(existentEmail,null));
        verify(repository).findByEmail(anyString());

    }
}
