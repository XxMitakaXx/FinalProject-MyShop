package org.example.finalprojectmyshop.product.models.enums;

public enum CategoryName {
    GAMES_AND_FREE_TIME("Games and free time"),
    BOOKSTORE("Bookstore"),
    ELECTRONICS_AND_COMPUTERS("Electronics and computers"),
    KIDS_AND_MOTHERS("Kids and mothers"),
    HEALTH_AND_BEAUTY("Health and beauty"),
    SPORT_AND_OUTDOOR("Sport and outdoor"),
    HOME_AND_GARDEN("Home and garden"),;

    private final String name;

    CategoryName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
