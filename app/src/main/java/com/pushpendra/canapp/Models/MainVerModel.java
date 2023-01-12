package com.pushpendra.canapp.Models;

import android.widget.Button;

public class MainVerModel {
    int image;
    String name;
    String timing;
    String price;
    String description;

    public MainVerModel(int image, String name, String timing, String price, String description) {
        this.image = image;
        this.name = name;
        this.timing = timing;
        this.price = price;
        this.description = description;
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

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
