package org.example.finalprojectmyshop.product.models.dtos;

public class AddProductPropertyDTO {
    private String name;
    private String value;

    public AddProductPropertyDTO() {}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}