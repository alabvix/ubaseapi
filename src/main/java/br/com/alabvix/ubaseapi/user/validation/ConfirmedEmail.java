package br.com.alabvix.ubaseapi.user.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Checks if email is equal to confirmed email.
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConfirmedEmailValidator.class)
public @interface ConfirmedEmail {
    String message() default "Email and confirmed email must match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
