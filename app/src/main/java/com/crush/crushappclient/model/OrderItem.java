package com.crush.crushappclient.model;

import java.util.ArrayList;
import java.util.List;

public class OrderItem {

    private MainDrink mainDrink;
    private List<Topping> toppingList;
    private int quantity;

    public OrderItem(MainDrink mainDrink, List<Topping> toppingList, int quantity) {
        this.mainDrink = mainDrink;
        this.toppingList = toppingList;
        this.quantity = quantity;
    }

    public OrderItem() {
        toppingList = new ArrayList<>();
    }

    public MainDrink getMainDrink() {
        return mainDrink;
    }

    public void setMainDrink(MainDrink mainDrink) {
        this.mainDrink = mainDrink;
    }

    public List<Topping> getToppingList() {
        return toppingList;
    }

    public void setToppingList(List<Topping> toppingList) {
        this.toppingList = toppingList;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        long price = mainDrink.getPrice();
        for (Topping topping : toppingList) {
            price += topping.getPrice();
        }
        return price;
    }
}
