package org.example.finalprojectmyshop.user.validation.annotations;

import jakarta.validation.Constraint;
import org.example.finalprojectmyshop.user.validation.validators.UniqueEmailValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "This email already exists!";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
