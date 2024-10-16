package org.example.finalprojectmyshop.user.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.service.UserService;
import org.example.finalprojectmyshop.user.validation.annotations.UniqueEmail;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserService userService;

    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        UserEntity userByEmail = this.userService.findUserByEmail(value);

        return userByEmail.getEmail() == null;
    }
}
