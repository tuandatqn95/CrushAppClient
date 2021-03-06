package com.crush.crushappclient.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderItem implements Serializable {

    private MainDrink maindrink;
    private List<Topping> toppings = new ArrayList<>();
    private long quantity;
    private long price;


    public OrderItem() {
    }

    public OrderItem(MainDrink maindrink, List<Topping> toppings, long quantity, long price) {
        this.maindrink = maindrink;
        this.toppings = toppings;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return price == orderItem.price &&
                Objects.equals(maindrink, orderItem.maindrink) &&
                Objects.equals(toppings, orderItem.toppings);
    }

    @Override
    public int hashCode() {

        return Objects.hash(maindrink, toppings, price);
    }

    public MainDrink getMaindrink() {
        return maindrink;
    }

    public void setMaindrink(MainDrink maindrink) {
        this.maindrink = maindrink;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
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
