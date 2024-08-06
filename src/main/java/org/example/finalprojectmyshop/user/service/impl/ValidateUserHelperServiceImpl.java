package org.example.finalprojectmyshop.user.service.impl;

import org.example.finalprojectmyshop.user.service.ValidateUserHelperService;
import org.springframework.stereotype.Service;

@Service
public class ValidateUserHelperServiceImpl implements ValidateUserHelperService {
    @Override
    public boolean validateUserFirstName(String firstName) {
        return firstName.trim().length() < 30 && firstName.trim().length() > 2;
    }

    @Override
    public boolean validateUserLastName(String lastName) {
        return lastName.trim().length() < 30 && lastName.trim().length() > 2;
    }

    @Override
    public boolean validateUserEmail(String email) {
        return !email.trim().isBlank() && email.contains("@");
    }
}
