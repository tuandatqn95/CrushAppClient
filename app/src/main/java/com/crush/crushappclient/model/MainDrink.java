package com.crush.crushappclient.model;

import java.io.Serializable;
import java.util.Objects;

public class MainDrink implements Serializable {

    private String name;
    private long price;
    private String imageURL;


    public MainDrink() {
    }

    public MainDrink(String name, long price, String imageURL) {
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainDrink mainDrink = (MainDrink) o;
        return price == mainDrink.price &&
                Objects.equals(name, mainDrink.name) &&
                Objects.equals(imageURL, mainDrink.imageURL);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, price, imageURL);
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


}
