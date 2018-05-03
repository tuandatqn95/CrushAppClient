package com.crush.crushappclient.model;

import java.util.Date;

public class Order {
    private int id;
    private int customerId;
    private int statusId;
    private int employeeId;
    private Date orderTime;
    private int totalPrice;
    private String address;
    private String note;

    public Order(int id, int customerId, int statusId, int employeeId, Date orderTime, int totalPrice, String address, String note) {
        this.id = id;
        this.customerId = customerId;
        this.statusId = statusId;
        this.employeeId = employeeId;
        this.orderTime = orderTime;
        this.totalPrice = totalPrice;
        this.address = address;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
