package com.crush.crushappclient.model;

import android.media.Image;

public class MainDrink {
    private int id;
    private String name;
    private int price;
    private Image image;
    private int categoryId;

    public MainDrink(int id, String name, int price, Image image, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}