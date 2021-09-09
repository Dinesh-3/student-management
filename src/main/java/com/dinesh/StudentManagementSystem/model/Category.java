package com.dinesh.StudentManagementSystem.model;

public enum Category {
    DEVELOPMENT("Development"),
    BUSINESS("Business"),
    FINANCE_ACCOUNTING("Finance&Accounting"),
    IT_SOFTWARE("IT Software"),
    OFFICE_PRODUCTIVITY("Office Productivity"),
    PERSONAL_DEVELOPMENT("Personal Development"),
    DESIGN("Design"),
    MARKETING("Marketing"),
    LIFESTYLE("Life Style"),
    PHOTOGRAPHY_VIDEO("Photography&Video"),
    HEALTH("Health"),
    FITNESS("Fitness"),
    SPORTS("Sports"),
    MUSIC("Music"),
    ACADEMIC("Academic");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
