package org.example.finalprojectmyshop.user.models.entities.enums;

public enum UserRole {
    USER("User"),
    ADMINISTRATOR("Administrator");

    private String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
