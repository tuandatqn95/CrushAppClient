package com.crush.crushappclient.model;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class OrderItem {

    private DocumentSnapshot mainDrink;
    private List<DocumentSnapshot> toppingList = new ArrayList<>();
    private int quantity;

    public OrderItem(DocumentSnapshot mainDrink) {
        this.mainDrink = mainDrink;
    }

    public OrderItem() {
    }

    public DocumentSnapshot getMainDrink() {
        return mainDrink;
    }

    public void setMainDrink(DocumentSnapshot mainDrink) {
        this.mainDrink = mainDrink;
    }

    public List<DocumentSnapshot> getToppingList() {
        return toppingList;
    }

    public void setToppingList(List<DocumentSnapshot> toppingList) {
        this.toppingList = toppingList;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        long price = mainDrink.toObject(MainDrink.class).getPrice();
        for (DocumentSnapshot topping : toppingList) {
            price += topping.toObject(Topping.class).getPrice();
        }
        return price;
    }
}
