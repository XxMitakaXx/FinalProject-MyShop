package org.example.finalprojectmyshop.product.models.dtos;

public class ReviewUserDataDTO {
    private String fullName;
    private String profilePictureUrl;

    public ReviewUserDataDTO() {}

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
}
