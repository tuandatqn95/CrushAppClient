package com.crush.crushappclient.model;

import com.google.firebase.firestore.DocumentSnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderItem implements Serializable {

    private String mainDrink;
    private List<Topping> toppingList = new ArrayList<>();
    private long quantity;
    private long price;

    public OrderItem(String mainDrink, List<Topping> toppingList, long quantity, long price) {
        this.mainDrink = mainDrink;
        this.toppingList = toppingList;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem() {
    }

    public String getMainDrink() {
        return mainDrink;
    }

    public void setMainDrink(String mainDrink) {
        this.mainDrink = mainDrink;
    }

    public List<Topping> getToppingList() {
        return toppingList;
    }

    public void setToppingList(List<Topping> toppingList) {
        this.toppingList = toppingList;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
