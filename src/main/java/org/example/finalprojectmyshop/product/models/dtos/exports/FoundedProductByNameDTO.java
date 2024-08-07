package org.example.finalprojectmyshop.product.models.dtos.exports;

public class FoundedProductByNameDTO {
    private long id;
    private String name;
    private String imageUrl;

    public FoundedProductByNameDTO() {}

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
