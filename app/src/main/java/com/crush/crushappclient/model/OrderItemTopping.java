package com.crush.crushappclient.model;

public class OrderItemTopping {
    private int orderItemId;
    private int toppingId;

    public OrderItemTopping(int orderItemId, int toppingId) {
        this.orderItemId = orderItemId;
        this.toppingId = toppingId;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getToppingId() {
        return toppingId;
    }

    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }
}
