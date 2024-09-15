package org.example.finalprojectmyshop.user.validation.annotations;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.finalprojectmyshop.user.models.dtos.imports.UserRegisterDTO;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserRegisterDTO> {

    private String message;

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(UserRegisterDTO userRegisterDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (userRegisterDTO.getPassword() == null || userRegisterDTO.getConfirmPassword() == null || userRegisterDTO.getPassword().isEmpty() || userRegisterDTO.getConfirmPassword().isEmpty()) {
            return true;
        }

        boolean isValid = userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword());

        if (!isValid) {
            constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class)
                    .buildConstraintViolationWithTemplate(this.message)
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return isValid;
    }
}
