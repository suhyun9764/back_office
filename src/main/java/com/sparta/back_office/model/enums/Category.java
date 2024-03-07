package com.sparta.back_office.model.enums;

public enum Category {
    SPRING("Spring"),
    REACT("React"),
    NODE("Node");

    private final String categoryName;

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
