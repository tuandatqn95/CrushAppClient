package com.crush.crushappclient.model;

import android.media.Image;
import android.widget.ImageView;

import java.io.Serializable;

public class MainDrink implements Serializable {

    private String name;
    private long price;
    private String imageUrl;
    private String categoryId;

    public MainDrink(){}

    public MainDrink(String name, long price, String imageUrl, String categoryId) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
