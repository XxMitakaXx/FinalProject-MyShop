package org.example.finalprojectmyshop.user.service;

public interface ValidateUserHelperService {
    boolean validateUserFirstName(String firstName);
    boolean validateUserLastName(String lastName);
    boolean validateUserEmail(String email);
}
