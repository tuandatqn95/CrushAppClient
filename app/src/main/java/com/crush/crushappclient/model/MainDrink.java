package com.crush.crushappclient.model;

import android.media.Image;
import android.widget.ImageView;

public class MainDrink {

    private String name;
    private String price;
    private String image;
    private String categoryId;

    public MainDrink(){}

    public MainDrink(String name, String price, String image, String categoryId) {

        this.name = name;
        this.price = price;
        this.image = image;
        this.categoryId = categoryId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
