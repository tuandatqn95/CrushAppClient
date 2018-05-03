package com.crush.crushappclient.model;

public class OrderItem {
    private int id;
    private int mainDrinkId;
    private int quantity;
    private int orderId;

    public OrderItem(int id, int mainDrinkId, int quantity, int orderId) {
        this.id = id;
        this.mainDrinkId = mainDrinkId;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMainDrinkId() {
        return mainDrinkId;
    }

    public void setMainDrinkId(int mainDrinkId) {
        this.mainDrinkId = mainDrinkId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
