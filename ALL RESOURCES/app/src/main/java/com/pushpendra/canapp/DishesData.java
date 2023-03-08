package com.pushpendra.canapp;

public class DishesData {
    String Category, Name, Description, Timing, Price, ImageLink;

    public DishesData(String category, String name, String description, String timing, String price, String imageLink) {
        Category = category;
        Name = name;
        Description = description;
        Timing = timing;
        Price = price;
        ImageLink = imageLink;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getImageLink() {
        return ImageLink;
    }

    public void setImageLink(String imageLink) {
        ImageLink = imageLink;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTiming() {
        return Timing;
    }

    public void setTiming(String timing) {
        Timing = timing;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
