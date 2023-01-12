package com.pushpendra.canapp.Models;

public class MainHorModel {
    int image;
    String name;
    String Category;

    public MainHorModel(int image, String name,String category) {
        this.image = image;
        this.name = name;
        this.Category = category;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
