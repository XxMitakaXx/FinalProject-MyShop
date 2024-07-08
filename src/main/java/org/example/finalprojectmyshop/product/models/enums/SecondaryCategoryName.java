package org.example.finalprojectmyshop.product.models.enums;

public enum SecondaryCategoryName {
    GAMING("Gaming", CategoryName.GAMES_AND_FREE_TIME),
    BOARD_GAMES_AND_PUZZLES("Board games and puzzles", CategoryName.GAMES_AND_FREE_TIME),
    MUSIC_AND_MOVIES("Music and movies", CategoryName.GAMES_AND_FREE_TIME),
    MERCHANDISE("Merchandise", CategoryName.GAMES_AND_FREE_TIME),
    BOOKS_AND_TEXTBOOKS("Books and textbooks", CategoryName.BOOKSTORE),
    SUPPLIES_AND_GIFTS("Supplies and gifts", CategoryName.BOOKSTORE),
    LAPTOPS_PC_AND_MONITORS("Laptops, PC and monitors", CategoryName.ELECTRONICS_AND_COMPUTERS),
    AUDIO_AND_HIFI("Audio and HI-FI", CategoryName.ELECTRONICS_AND_COMPUTERS),
    SMARTPHONES_AND_SMART_DEVICES("Smartphones and smart devices", CategoryName.ELECTRONICS_AND_COMPUTERS),
    TV_FOTO_AND_VIDEO("TV, Foto and video", CategoryName.ELECTRONICS_AND_COMPUTERS),
    TOYS("Toys", CategoryName.KIDS_AND_MOTHERS),
    MOTHERS_AND_BABY("Mothers and baby", CategoryName.KIDS_AND_MOTHERS),
    COSMETICS_AND_PERFUMES("Cosmetics and perfumes", CategoryName.HEALTH_AND_BEAUTY),
    PHARMACY_AND_NUTRITIONAL_SUPPLEMENTS("Pharmacy and nutritional supplements", CategoryName.HEALTH_AND_BEAUTY),
    SPORT_AND_CAMPING("Sport and camping", CategoryName.SPORT_AND_OUTDOOR),
    LIFESTYLE_AND_FITNESS("Lifestyle and fitness", CategoryName.SPORT_AND_OUTDOOR),
    FOR_HOME_AND_OFFICE("For home and office", CategoryName.HOME_AND_GARDEN),
    SMALL_ELECTRICAL_APPLIANCES("Small electrical appliances", CategoryName.HOME_AND_GARDEN);

    private final String name;
    private final CategoryName categoryName;

    SecondaryCategoryName(String name, CategoryName categoryName) {
        this.name = name;
        this.categoryName = categoryName;
    }

    public String getName() {
        return this.name;
    }

    public CategoryName getCategoryName() {
        return this.categoryName;
    }


}
