package org.example.finalprojectmyshop.user.models.entities.enums;

public enum UserRole {
    USER("User"),
    MODERATOR("Moderator"),
    ADMIN("Admin");

    private String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
