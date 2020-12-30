package br.com.alabvix.ubaseapi.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmedPasswordValidator implements ConstraintValidator<ConfirmedPassword, SignUpUser> {
    @Override
    public boolean isValid(SignUpUser signUpUser, ConstraintValidatorContext constraintValidatorContext) {
        return signUpUser.password.equalsIgnoreCase(signUpUser.confirmPassword);
    }
}
