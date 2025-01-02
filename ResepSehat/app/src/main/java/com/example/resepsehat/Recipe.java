package com.example.resepsehat.model;

public class Recipe {
    private int id;
    private String name;
    private String description;
    private String time;
    private String calories;
    private int imageResId;
    private String bahan;
    private String cara;
    // Tambah getter untuk bahan dan cara
    public String getBahan() {
        return bahan;
    }

    public String getCara() {
        return cara;
    }
    // Update constructor
    public Recipe(int id, String name, String description, String time,
                  String calories, int imageResId, String bahan, String cara) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.time = time;
        this.calories = calories;
        this.imageResId = imageResId;
        this.bahan = bahan;
        this.cara = cara;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String getCalories() {
        return calories;
    }

    public int getImageResId() {
        return imageResId;
    }
}
