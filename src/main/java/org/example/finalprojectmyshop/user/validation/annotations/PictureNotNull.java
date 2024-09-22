package org.example.finalprojectmyshop.user.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.finalprojectmyshop.user.validation.validators.PictureNotNullValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PictureNotNullValidator.class)
public @interface PictureNotNull {
    String message() default "Choose a picture";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
