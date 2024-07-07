package org.example.finalprojectmyshop.product.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.example.finalprojectmyshop.product.models.entities.Category;
import org.example.finalprojectmyshop.product.models.entities.ProductProperty;
import org.example.finalprojectmyshop.product.models.entities.SecondaryCategory;
import org.example.finalprojectmyshop.product.models.enums.SecondaryCategoryName;

import java.io.File;
import java.util.Set;

public class AddProductDTO {
    @Size(min = 2, max = 50)
    @NotBlank
    private String name;

    @Positive
    private double price;

    @Size(min = 10, max = 200)
    private String description;

    private Set<ProductProperty> properties;

    private File images;

    private SecondaryCategoryName secondaryCategoryName;

    public AddProductDTO() {}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProductProperty> getProperties() {
        return this.properties;
    }

    public void setProperties(Set<ProductProperty> properties) {
        this.properties = properties;
    }

    public File getImages() {
        return this.images;
    }

    public void setImages(File images) {
        this.images = images;
    }

    public SecondaryCategoryName getSecondaryCategoryName() {
        return this.secondaryCategoryName;
    }

    public void setSecondaryCategoryName(SecondaryCategoryName secondaryCategoryName) {
        this.secondaryCategoryName = secondaryCategoryName;
    }
}
