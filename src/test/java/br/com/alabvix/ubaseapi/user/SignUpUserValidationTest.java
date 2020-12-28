package br.com.alabvix.ubaseapi.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class SignUpUserValidationTest {

    private Validator validator;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenAllFieldsNull_shouldGiveConstraintsViolation() {
        SignUpUser signUpUser =
                new SignUpUser(null, null, null, null, null);

        Set<ConstraintViolation<SignUpUser>> violations = validator.validate(signUpUser);
        assertFalse(violations.isEmpty());

    }

    @Test
    public void whenEmailIsEmpty_shouldGiveConstraintViolation() {
        // Given
        SignUpUser signUpUser =
                new SignUpUser("Teste",
                        "",
                        "",
                        "123ABC",
                        "123ABC");

        // When
        Set<ConstraintViolation<SignUpUser>> violations = validator.validate(signUpUser);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());

    }

    @Test
    public void whenEmailIsNull_shouldGiveConstraintViolation() {
        // Given
        SignUpUser signUpUser =
                new SignUpUser("Teste",
                        null,
                        "",
                        "123ABC",
                        "123ABC");

        // When
        Set<ConstraintViolation<SignUpUser>> violations = validator.validate(signUpUser);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());

    }

    @Test
    public void whenInvalidEmail_shouldGiveConstraintViolation() {
        // Given
        SignUpUser signUpUser =
                new SignUpUser("Teste",
                        "hdhdy@",
                        "",
                        "123ABC",
                        "123ABC");

        // When
        Set<ConstraintViolation<SignUpUser>> violations = validator.validate(signUpUser);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());

    }


}
