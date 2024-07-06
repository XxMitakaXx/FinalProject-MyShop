package org.example.finalprojectmyshop.mediaFile.models.enums;

public enum ImageType {
    USER("users-images/", "user/"),
    PRODUCT("product-images/", "products/");

    private final String cloudFolderPath;
    private final String localFolderPath;

    ImageType(String cloudFolderPath, String localFolderPath) {
        this.cloudFolderPath = cloudFolderPath;
        this.localFolderPath = localFolderPath;
    }

    public String getCloudFolderPath() {
        return this.cloudFolderPath;
    }

    public String getLocalFolderPath() {
        return this.localFolderPath;
    }
}
