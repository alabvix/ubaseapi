package br.com.alabvix.ubaseapi.user;

import br.com.alabvix.ubaseapi.user.validation.ConfirmedPassword;
import br.com.alabvix.ubaseapi.user.validation.ConfirmedPasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConfirmedPasswordValidatorTest {

    @Mock
    private ConfirmedPassword confirmedPassword;

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    private ConfirmedPasswordValidator validator;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        validator = new ConfirmedPasswordValidator();
        validator.initialize(confirmedPassword);
    }

    @Test
    @DisplayName("Given no match passwords then validation must fail")
    public void isValid_noMatchPassword_mustFail() {

        SignUpUser signUpUser = new SignUpUser("UserTest",
                "user@user.com",
                "user@user.com",
                "123458",
                "123");

        assertFalse(validator.isValid(signUpUser,constraintValidatorContext));

    }

    @Test
    @DisplayName("Given match passwords then validation must pass")
    public void isValid_MatchPassword_mustPass() {

        SignUpUser signUpUser = new SignUpUser("UserTest",
                "user@user.com",
                "user@user.com",
                "123458",
                "123458");

        assertTrue(validator.isValid(signUpUser,constraintValidatorContext));

    }

}
