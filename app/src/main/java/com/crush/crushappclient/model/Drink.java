package com.crush.crushappclient.model;

import java.io.Serializable;

public class Drink implements Serializable {
    private int drinkId;
    private int toppingId;

    public Drink(int drinkId, int toppingId) {
        this.drinkId = drinkId;
        this.toppingId = toppingId;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public int getToppingId() {
        return toppingId;
    }

    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }
}
