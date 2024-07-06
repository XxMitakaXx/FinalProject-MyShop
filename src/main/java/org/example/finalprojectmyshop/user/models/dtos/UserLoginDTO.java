package org.example.finalprojectmyshop.user.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserLoginDTO {
    @Email
    private String email;

    @NotBlank
    private String password;

    public UserLoginDTO() {}

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
}
