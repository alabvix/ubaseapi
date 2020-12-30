package br.com.alabvix.ubaseapi.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConfirmedEmailValidatorTest {

    @Mock
    private ConfirmedEmail confirmedEmail;

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    private ConfirmedEmailValidator validator;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        validator = new ConfirmedEmailValidator();
        validator.initialize(confirmedEmail);
    }

    @Test
    @DisplayName("Given no match emails then validation must fail")
    public void isValid_noMatchEmail_mustFail() {

        SignUpUser signUpUser = new SignUpUser("UserTest",
                "user@22user.com",
                "user@user.com",
                "123458",
                "123");

        assertFalse(validator.isValid(signUpUser,constraintValidatorContext));

    }

    @Test
    @DisplayName("Given match emails then validation must pass")
    public void isValid_MatchPassword_mustPass() {

        SignUpUser signUpUser = new SignUpUser("UserTest",
                "user@user.com",
                "user@user.com",
                "123458",
                "123458");

        assertTrue(validator.isValid(signUpUser,constraintValidatorContext));

    }
}
