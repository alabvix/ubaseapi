package br.com.alabvix.ubaseapi.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Checks if email is equal to confirmed email.
 */
public class ValidEmailConfirmedEmailValidator implements ConstraintValidator<ValidEmailConfirmedEmail, SignUpUser> {

    @Override
    public void initialize(ValidEmailConfirmedEmail constraintAnnotation) {}

    public boolean isValid(SignUpUser signUpUser, ConstraintValidatorContext context) {
        return (signUpUser.email.equalsIgnoreCase(signUpUser.confirmEmail));
    }

}
