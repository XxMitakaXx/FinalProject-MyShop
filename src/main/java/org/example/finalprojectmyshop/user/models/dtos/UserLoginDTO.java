package org.example.finalprojectmyshop.user.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {

    @Email
    @Size(min = 2, max = 30)
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 30)
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
