package org.example.finalprojectmyshop.user.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.finalprojectmyshop.user.validation.annotations.PictureNotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PictureNotNullValidator implements ConstraintValidator<PictureNotNull, MultipartFile> {
    @Override
    public void initialize(PictureNotNull constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        return !multipartFile.isEmpty();
    }
}
