package org.example.finalprojectmyshop.product.models.dtos.imports;

import jakarta.validation.constraints.*;
import org.example.finalprojectmyshop.product.models.enums.SecondaryCategoryName;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class AddProductDTO {
    @Size(min = 2, max = 50)
    @NotBlank
    private String name;

    @Positive
    private Double price;

    private List<AddProductPropertyDTO> properties;

    private MultipartFile firstImage;

    private MultipartFile secondImage;

    private MultipartFile thirdImage;

    private MultipartFile fourthImage;

    private MultipartFile fifthImage;

    @NotNull
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

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<AddProductPropertyDTO> getProperties() {
        return this.properties;
    }

    public void setProperties(List<AddProductPropertyDTO> properties) {
        this.properties = properties;
    }

    public MultipartFile getFirstImage() {
        return this.firstImage;
    }

    public void setFirstImage(MultipartFile firstImage) {
        this.firstImage = firstImage;
    }

    public MultipartFile getSecondImage() {
        return this.secondImage;
    }

    public void setSecondImage(MultipartFile secondImage) {
        this.secondImage = secondImage;
    }

    public MultipartFile getThirdImage() {
        return this.thirdImage;
    }

    public void setThirdImage(MultipartFile thirdImage) {
        this.thirdImage = thirdImage;
    }

    public MultipartFile getFourthImage() {
        return this.fourthImage;
    }

    public void setFourthImage(MultipartFile fourthImage) {
        this.fourthImage = fourthImage;
    }

    public MultipartFile getFifthImage() {
        return this.fifthImage;
    }

    public void setFifthImage(MultipartFile fifthImage) {
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
