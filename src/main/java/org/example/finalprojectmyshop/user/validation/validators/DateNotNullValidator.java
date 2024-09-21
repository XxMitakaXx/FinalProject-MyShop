package org.example.finalprojectmyshop.user.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.finalprojectmyshop.user.validation.annotations.DateNotNull;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateNotNullValidator implements ConstraintValidator<DateNotNull, LocalDate> {
    @Override
    public void initialize(DateNotNull constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate birthdate, ConstraintValidatorContext constraintValidatorContext) {
        return birthdate != null;
    }
}
