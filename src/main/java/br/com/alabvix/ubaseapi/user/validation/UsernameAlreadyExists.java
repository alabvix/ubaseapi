package br.com.alabvix.ubaseapi.user.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameAlreadyExistsValidator.class)
public @interface UsernameAlreadyExists {
    String message() default "Username already exists in the database";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
