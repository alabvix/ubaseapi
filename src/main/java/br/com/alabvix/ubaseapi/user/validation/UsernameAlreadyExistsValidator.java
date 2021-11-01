package br.com.alabvix.ubaseapi.user.validation;

import br.com.alabvix.ubaseapi.user.User;
import br.com.alabvix.ubaseapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;


public class UsernameAlreadyExistsValidator implements ConstraintValidator<UsernameAlreadyExists, String> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UsernameAlreadyExists constraintAnnotation) {}

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> opUser = repository.findByUsername(userName);
        return (opUser.isEmpty());
    }

}
