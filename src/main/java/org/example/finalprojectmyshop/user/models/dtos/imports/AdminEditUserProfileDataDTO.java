package org.example.finalprojectmyshop.user.models.dtos.imports;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class AdminEditUserProfileDataDTO {

    @NotEmpty(message = "Enter your first name!")
    @Size(min = 2, max = 30, message = "Size must be between 2 and 30!")
    private String firstName;

    @NotEmpty(message = "Enter your last name!")
    @Size(min = 2, max = 30, message = "Size must be between 2 and 30!")
    private String lastName;

    @Email(message = "Must be a well-formed email address!")
    @NotEmpty(message = "Enter your email!")
    private String email;

    @NotEmpty(message = "Enter your phone number!")
    @Size(min = 10, max = 10, message = "Enter valid phone number!")
    private String phoneNumber;

    private LocalDate birthDate;

    private MultipartFile profilePicture;

    public AdminEditUserProfileDataDTO() {}

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
