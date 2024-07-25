package org.example.finalprojectmyshop.order.models.dtos.exports;

public class CartProductDTO {
    private long id;
    private String name;
    private String imageUrl;
    private int priceBeforePoint;
    private int priceAfterPoint;

    public CartProductDTO() {

    }

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

    public int getPriceBeforePoint() {
        return this.priceBeforePoint;
    }

    public void setPriceBeforePoint(int priceBeforePoint) {
        this.priceBeforePoint = priceBeforePoint;
    }

    public int getPriceAfterPoint() {
        return this.priceAfterPoint;
    }

    public void setPriceAfterPoint(int priceAfterPoint) {
        this.priceAfterPoint = priceAfterPoint;
    }
}
