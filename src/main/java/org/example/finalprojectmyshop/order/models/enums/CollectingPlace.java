package org.example.finalprojectmyshop.order.models.enums;

public enum CollectingPlace {
    EKONT("Ekont office"),
    SPEEDY("Speedy office"),
    ON_YOUR_ADDRESS("On your address");

    private final String value;

    CollectingPlace(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
