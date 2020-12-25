package br.com.alabvix.ubaseapi.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailAlreadyExistsValidator.class)
public @interface EmailAlreadyExists {
    String message() default "Email already exists in the database";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
