package com.crush.crushappclient.model;

import android.media.Image;
import android.widget.ImageView;

import java.io.Serializable;

public class MainDrink implements Serializable {

    private String name;
    private String price;
    private String imageUrl;
    private String categoryId;

    public MainDrink(){}

    public MainDrink(String name, String price, String image, String categoryId) {

        this.name = name;
        this.price = price;
        this.imageUrl = image;
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
        return imageUrl;
    }

    public void setImage(String image) {
        this.imageUrl = image;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
