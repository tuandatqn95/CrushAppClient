package com.crush.crushappclient.fragment.model;

import java.io.Serializable;

public class Category implements Serializable {
    public static final String KEY_CATEGORY_NAME = "name";
    private String imageURL ;
    private String name;

    public Category() {
    }

    public Category(String imageURL, String name) {
        this.imageURL = imageURL;
        this.name = name;
    }

    public static String getKeyCategoryName() {
        return KEY_CATEGORY_NAME;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
