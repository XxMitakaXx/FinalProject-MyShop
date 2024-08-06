package org.example.finalprojectmyshop.user.models.dtos.imports;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class UserRegisterDTO {

    @Size(min = 2, max = 20, message = "Size must be between 2 and 20!")
    @NotBlank(message = "First name cannot be empty!")
    private String firstName;

    @Size(min = 2, max = 20, message = "Size must be between 2 and 20!")
    @NotBlank(message = "Last name cannot be empty!")
    private String lastName;

    @Email(message = "Must be a well-formed email address!")
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @NotBlank(message = "Password cannot be empty!")
    @Size(min = 2, max = 30, message = "Password must be between 2 and 30")
    private String password;

    @NotBlank(message = "Password cannot be empty!")
    private String confirmPassword;

    @Size(min = 10, max = 10, message = "Enter valid phone number!")
    @NotBlank(message = "Phone number cannot be empty!")
    private String phoneNumber;

    private LocalDate birthDate;

    private MultipartFile profilePicture;

    public UserRegisterDTO() {}

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public MultipartFile getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(MultipartFile profilePicture) {
        this.profilePicture = profilePicture;
    }
}
