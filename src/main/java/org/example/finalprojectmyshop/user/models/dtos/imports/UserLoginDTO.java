package org.example.finalprojectmyshop.user.models.dtos.imports;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {

    @Email(message = "Must be a well-formed email address!")
    @NotBlank(message = "Email cannot be empty!")
    private String email;

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
