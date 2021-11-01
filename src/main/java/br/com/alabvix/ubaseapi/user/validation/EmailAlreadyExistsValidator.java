package br.com.alabvix.ubaseapi.user.validation;

import br.com.alabvix.ubaseapi.user.User;
import br.com.alabvix.ubaseapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class EmailAlreadyExistsValidator implements ConstraintValidator<EmailAlreadyExists, String> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(EmailAlreadyExists constraintAnnotation) {}

    public boolean isValid(String email, ConstraintValidatorContext context) {
        Optional<User> opUser = repository.findByEmail(email);
        return (opUser.isEmpty());
    }
}
