package org.example.finalprojectmyshop.product.models.dtos;

import jakarta.validation.constraints.*;
import org.example.finalprojectmyshop.product.models.enums.SecondaryCategoryName;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddProductDTO {
    @Size(min = 2, max = 50)
    @NotBlank
    private String name;

    @Positive
    private double price;

    @Size(min = 10, max = 500)
    private String description;

    private List<AddProductPropertyDTO> properties;

    private File firstImage;

    private File secondImage;

    private File thirdImage;

    private File fourthImage;

    private File fifthImage;

    private SecondaryCategoryName secondaryCategoryName;

    @PositiveOrZero
    private int quantity;

    public AddProductDTO() {
        this.properties = new ArrayList<>();
    }

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

    public List<AddProductPropertyDTO> getProperties() {
        return this.properties;
    }

    public void setProperties(List<AddProductPropertyDTO> properties) {
        this.properties = properties;
    }

    public File getFirstImage() {
        return this.firstImage;
    }

    public void setFirstImage(File firstImage) {
        this.firstImage = firstImage;
    }

    public File getSecondImage() {
        return this.secondImage;
    }

    public void setSecondImage(File secondImage) {
        this.secondImage = secondImage;
    }

    public File getThirdImage() {
        return this.thirdImage;
    }

    public void setThirdImage(File thirdImage) {
        this.thirdImage = thirdImage;
    }

    public File getFourthImage() {
        return this.fourthImage;
    }

    public void setFourthImage(File fourthImage) {
        this.fourthImage = fourthImage;
    }

    public File getFifthImage() {
        return this.fifthImage;
    }

    public void setFifthImage(File fifthImage) {
        this.fifthImage = fifthImage;
    }

    public SecondaryCategoryName getSecondaryCategoryName() {
        return this.secondaryCategoryName;
    }

    public void setSecondaryCategoryName(SecondaryCategoryName secondaryCategoryName) {
        this.secondaryCategoryName = secondaryCategoryName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity( int quantity) {
        this.quantity = quantity;
    }
}
