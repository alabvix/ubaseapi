package br.com.alabvix.ubaseapi.user;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class EmailAlreadyExistsValidator implements ConstraintValidator<EmailAlreadyExists, SignUpUser> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(EmailAlreadyExists constraintAnnotation) {}

    public boolean isValid(SignUpUser signUpUser, ConstraintValidatorContext context) {
        Optional<User> opUser = repository.findByEmail(signUpUser.email);
        return (!opUser.isPresent());
    }
}
